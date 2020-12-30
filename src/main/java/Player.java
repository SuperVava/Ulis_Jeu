import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    public PImage texture;
    PApplet processing;
    ShootList shoots;
    Screen screen;
    int sens;
    int positionX;
    int positionY;

    int lastPositionX;
    int lastPositionY;

    public Player(PApplet processing, ShootList shoots, Screen screen) {
        this.processing = processing;
        this.screen = screen;
        this.shoots = shoots;
        texture = processing.loadImage("ulis.png");
    }

    public int getX(){
        return positionX;
    }
    public int getY(){
        return positionY;
    }

    public void spawn(){
        positionX = 10;
        positionY = 10;
        sens = 1;
        set();
    }

    void shoot(){
        shoots.newShoot(positionX, positionY, sens);
    }

    private void set(){
            screen.erase(texture, lastPositionX, lastPositionY);
        try {
            processing.pushMatrix();
            processing.rotate(PApplet.radians(90));
            screen.set(texture, positionX, positionY, ElementType.PLAYER);
            processing.popMatrix();
        }
        catch (Colision colision){
            if(colision.getType() == ElementType.WALL) {
                screen.erase(texture, positionX, positionY);
                positionX = lastPositionX;
                positionY = lastPositionY;
                set();
            }else if (colision.getType() == ElementType.DOOR){
                screen.erase(texture, positionX, positionY);
                passage();
            }
        }
    }

    void move(String direction) {
        lastPositionX = positionX;
        lastPositionY = positionY;

        if (direction == "front") {
            positionY -= 1;
            sens = 1;
        }
        if (direction == "left") {
            positionX -= 1;
            sens = 2;

        }
        if (direction == "back") {
            positionY += 1;
            sens = 3;
        }
        if (direction == "right") {
            positionX += 1;
            sens = 4;
        }
        set();

    }

    private void passage(){
        //passage d'une porte à l'autre
        screen.erase(texture, positionX, positionY);
        System.out.println("demande d'impression");
        screen.draw();
        System.out.println("passage...");
        processing.delay(1000);
        lastPositionX = positionX;
        lastPositionY = positionY;

        //test si la porte est celle de droite ou de gauche
        if(positionX > 100) {
            positionX = 1;
            positionY = 70;
            System.out.println("passage effectué");
        }
        else{
            positionX = 240;
            positionY = 114;
        }
    }
}
