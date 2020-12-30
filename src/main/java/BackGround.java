import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PImage;

public class BackGround {
    private PImage screen1, screen2, screen3;
    private PApplet processing;
    Screen screen;
    private PImage listScreen[] = {screen1, screen2, screen3};
    private int currentScreen;
    private int currentColision;
    private int[] pixelArray;

    public BackGround(PApplet processing, Screen screen) {
        this.screen = screen;
        this.processing = processing;
        this.listScreen[0] = processing.loadImage("fond.png");
        this.listScreen[1] = processing.loadImage("colision.png");
    }

    public void set(int screen) {
        currentScreen = screen;
        pixelArray = listScreen[currentScreen].pixels;
        currentColision = screen + 1;
        this.screen.setCurrentBackground(pixelArray, listScreen[currentColision]);
    }

    public void load(Item item)  {
        for (int i = 0; i < item.getTextureBroken().height; i++) {
            for (int j = 0; j < item.getTextureBroken().width; j++) {
                //n'imprime que si le pixel est coloré
                if (item.getTextureBroken().pixels[i * item.getTextureBroken().width + j] < 0) {
                    pixelArray[item.positionX + (item.positionY * listScreen[currentScreen].width) + j + (i * listScreen[currentScreen].width)] = item.getTextureBroken().pixels[i * item.getTextureBroken().width + j];
                }
            }
        }
        try {
            screen.set(item.getTextureBroken(), item.positionX, item.positionY, ElementType.FREE);
        } catch (Colision colision) {
            colision.printStackTrace();
        }
        screen.draw();
    }

}
