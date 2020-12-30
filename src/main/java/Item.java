import processing.core.PApplet;
import processing.core.PImage;

public class Item {
    PApplet processing;
    PImage texture;
    PImage textureBroken;
    int positionX;
    int positionY;
    int itemNumber;

    public PImage getTexture() {
        return texture;
    }

    public PImage getTextureBroken() {
        return textureBroken;
    }

    public Item(PApplet processing, String texture, int positionX, int positionY, int index) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.processing = processing;
        this.itemNumber = index;
        this.texture = processing.loadImage(texture + ".png");
        this.textureBroken = processing.loadImage(texture + "_brocken.png");
    }
}
