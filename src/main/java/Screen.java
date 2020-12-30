import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Screen {
    PApplet processing;
    int grillWidth;
    int grillHeight;
    int[][] pixelArray;
    int[] currentBackground;
    PImage currentColision;
    CollisionController collisionController;


    public Screen(PApplet processing) {
        this.processing = processing;
        this.grillWidth = 256;
        this.grillHeight = 144;
        this.pixelArray = new int[grillWidth * grillHeight][3];
        this.collisionController = new CollisionController(grillWidth, grillHeight);
        for (int i = 0; i < grillHeight; i++) {
            for (int j = 0; j < grillWidth; j++) {
                /*
                pixelArray est un tableau de pixel qui comporte 3 informations pour chaques pixels:
                -la position en X sur l'écran de l'ordinateur
                -la position en Y sur l'écran de l'ordinateur
                -la couleur affichée du pixel
                 */

                pixelArray[i * grillWidth + j] = new int[]{j * pixelSize(), i * pixelSize(), 0};
            }
        }

    }

    private int foundPosition(int positionX, int positionY, int i, int j) {
        return positionX + (positionY * grillWidth) + j + (i * grillWidth);
    }

    public void setCurrentBackground(int[] currentBackground, PImage currentColision) {
        this.currentBackground = currentBackground;
        this.currentColision = currentColision;
        setFont();
    }


    public int pixelSize() {
        return Math.min(processing.displayHeight / 144, processing.displayWidth / 256);
    }

    //ajoute une image dans la grille de pixels
    public void set(PImage texture, int positionX, int positionY, int type) throws Colision {
        for (int i = 0; i < texture.height; i++) {
            for (int j = 0; j < texture.width; j++) {
                //n'imprime que si le pixel est coloré
                if (texture.pixels[i * texture.width + j] < 0) {
                    //teste si le pixel entre en colision
                    collisionController.testForCollision(foundPosition(positionX, positionY, i, j), type);
                    try {
                        pixelArray[foundPosition(positionX, positionY, i, j)][2] = texture.pixels[i * texture.width + j];
                    } catch (Exception colision) {
                        throw colision;
                    }
                }

            }
        }
    }


    //supprime une image de la grille de pixel en remplaçant par l'image de fond
    public void erase(PImage texture, int positionX, int positionY) {
        //traite tout les pixels de l'image
        for (int i = 0; i < texture.height; i++) {
            for (int j = 0; j < texture.width; j++) {
                //n'imprime que si le pixel est coloré
                if (texture.pixels[i * texture.width + j] < 0) {
                    pixelArray[foundPosition(positionX, positionY, i, j)][2] = currentBackground[foundPosition(positionX, positionY, i, j)];
                    setCollisionFont(foundPosition(positionX, positionY, i, j));
                }

            }
        }
    }

    //charge l'image de fond et ses collisions dans la grille
    public void setFont() {
        for (int i = 0; i < currentBackground.length; i++) {
            //colorisation de tout pixels avec l'image de fond
            pixelArray[i][2] = currentBackground[i];
            setCollisionFont(i);
        }
    }

    private void setCollisionFont(int pixelIndex) {
        if (currentColision.pixels[pixelIndex] == -16777216) {
            collisionController.setPixelArray(pixelIndex, ElementType.WALL);

        } else if (currentColision.pixels[pixelIndex] == -16710214) {
            collisionController.setPixelArray(pixelIndex, ElementType.HALF_WALL);

        } else if (currentColision.pixels[pixelIndex] == -3604225) {
            collisionController.setPixelArray(pixelIndex, ElementType.DOOR);

        } else if (currentColision.pixels[pixelIndex] < 0) {
            System.out.println(currentColision.pixels[pixelIndex]);
        } else collisionController.setPixelArray(pixelIndex, ElementType.FREE);
    }


    //
    public void draw() {
        processing.rectMode(PConstants.CORNER);
        processing.noStroke();
        processing.colorMode(PConstants.ARGB);
        for (int[] ints : pixelArray) {
            processing.fill(ints[2]);
            processing.rect(ints[0], ints[1], pixelSize(), pixelSize());
        }
    }
}
