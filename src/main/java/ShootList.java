import Exeptions.Colision;
import processing.core.PApplet;
import processing.core.PImage;

public class ShootList {
    PApplet processing;
    Screen screen;
    ItemList itemList;
    public int positions[][];
    public int lastPositions[][];
    int numberOfShoots;
    int maximum;
    PImage texture;

    public ShootList(PApplet processing, Screen screen, ItemList itemList) {
        this.processing = processing;
        this.screen = screen;
        this.itemList = itemList;
        this.maximum = 10;
        this.texture = processing.loadImage("shoot.png");
        this.positions = new int[maximum][3];
        this.lastPositions = new int[maximum][2];
        numberOfShoots = 0;
    }

    void newShoot(int ulisX, int ulisY, int direction) {
        positions[numberOfShoots][0] = ulisX + 13;
        positions[numberOfShoots][1] = ulisY + 1;
        positions[numberOfShoots][2] = direction;
        if (numberOfShoots >= maximum - 1) numberOfShoots = 0;
        else numberOfShoots++;
    }

    void update() {
        for (int i = 0; i < maximum; i++) {
            lastPositions[i] = new int[]{positions[i][0], positions[i][1]};
            if (positions[i][2] == 1) {
                positions[i][1] -= 1;
            }
            if (positions[i][2] == 2) {
                positions[i][0] -= 1;
            }
            if (positions[i][2] == 3) {
                positions[i][1] += 1;
            }
            if (positions[i][2] == 4) {
                positions[i][0] += 1;
            }

        }
        set();
    }

    private void set() {
        for (int i = 0; i < positions.length; i++) {
            if (positions[i][2] != 0) {
                screen.erase(texture, lastPositions[i][0], lastPositions[i][1]);
                try {
                    screen.set(texture, positions[i][0], positions[i][1], ElementType.BULLET);
                } catch (Colision colision) {
                    positions[i] = new int[]{0, 0, 0};
                    if(colision.getType() > 100 && colision.getType() < 200){
                        System.out.println("ordre de casser in shootList");
                        itemList.brockItem(colision.getType());
                    }
                }
            }
        }
    }
}
