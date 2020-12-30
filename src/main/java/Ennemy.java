import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PImage;

public class Ennemy {
    PApplet processing;
    Player ulis;
    PImage texture;
    Screen screen;
    public int positionX;
    public int positionY;
    private int lastPositionX;
    private int lastPositionY;

    public Ennemy(PApplet processing, Player ulis, Screen screen) {
        this.processing = processing;
        this.ulis = ulis;
        this.screen = screen;
        this.texture = processing.loadImage("tom.png");
        spawn((int)processing.random(1, 3));
    }

    private void spawn(int point) {
        if(point == 1){
            positionX = 10;
            positionY = 70;
        }
        if(point == 2){
            positionX = 250;
            positionY = 114;
        }

    }

    void set(){
        try {
            screen.set(texture, positionX, positionY, ElementType.ENNEMY);
        }
        catch (Colision colision){
            if(colision.getType() == ElementType.WALL) {
                positionX = lastPositionX;
                positionY = lastPositionY;
                set();
            }
            if(colision.getType() == ElementType.BULLET){
                spawn((int)processing.random(1, 3));
            }
        }
    }

    void move() {
        lastPositionX = positionX;
        lastPositionY = positionY;

        screen.erase(texture, lastPositionX, lastPositionY);

        if (positionX > ulis.getX()) {
            positionX -= 1;
        }
        if (positionX < ulis.getX()) {
            positionX += 1;
        } else {
        }
        if (positionY > ulis.getY()) {
            positionY -= 1;
        }
        if (positionY < ulis.getY()) {
            positionY += 1;
        } else {

        }
        set();
    }

}
