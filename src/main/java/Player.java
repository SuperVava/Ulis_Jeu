import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    public PImage[] texture;
    PApplet processing;
    ShootList shoots;
    Screen screen;
    int sens;
    int lastSens;
    int positionX;
    int positionY;

    int lastPositionX;
    int lastPositionY;

    public Player(PApplet processing, ShootList shoots, Screen screen) {
        this.processing = processing;
        this.screen = screen;
        this.shoots = shoots;
        texture = new PImage[4];
        texture = new PImage[]{processing.loadImage("ulis.png"), processing.loadImage("ulis_right.png"), processing.loadImage("ulis_down.png"), processing.loadImage("ulis_left.png")};
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
            screen.erase(texture[lastSens], lastPositionX, lastPositionY);
        try {

            screen.set(texture[sens], positionX, positionY, ElementType.PLAYER);
        }
        catch (Colision colision){
            if(colision.getType() == ElementType.WALL) {
                screen.erase(texture[sens], positionX, positionY);
                positionX = lastPositionX;
                positionY = lastPositionY;
                sens = lastSens;
                set();
            }else if (colision.getType() == ElementType.DOOR){
                screen.erase(texture[sens], positionX, positionY);
                passage();
            }
        }
    }

    void move(String direction) {
        lastPositionX = positionX;
        lastPositionY = positionY;
        lastSens = sens;

        if (direction == "front") {
            positionY -= 1;
            sens = 0;
        }
        if (direction == "right") {
            positionX += 1;
            sens = 1;

        }
        if (direction == "back") {
            positionY += 1;
            sens = 2;
        }
        if (direction == "left") {
            positionX -= 1;
            sens = 3;
        }
        set();

    }

    private void passage(){
        //passage d'une porte à l'autre
        screen.erase(texture[sens], positionX, positionY);
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
