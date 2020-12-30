import Exeptions.Colision;
import processing.core.PApplet;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> itemList;
    Screen screen;
    BackGround backGround;

    public ItemList(PApplet processing, Screen screen, BackGround backGround) {
        this.screen = screen;
        this.backGround = backGround;
        itemList = new ArrayList<>();
        itemList.add(new Item(processing, "casserole", 190, 60, 101));

    }

    public void set() {
        for (int i = 0; i < itemList.size(); i++) {
            try {
                screen.set(itemList.get(i).getTexture(), itemList.get(i).positionX, itemList.get(i).positionY, itemList.get(i).itemNumber);
            } catch (Colision colision) {
                colision.printStackTrace();
            }
        }
    }

    public void brockItem(int index) {
        index -= 101;
        System.out.println("ordre de casser in itemList");
        screen.erase(itemList.get(index).texture, itemList.get(index).positionX, itemList.get(index).positionY);
        backGround.load(itemList.get(index));
    }
}
