import processing.core.PApplet;

public class UlisJeu extends PApplet {
    Screen screen;
    BackGround backGround;
    Player ulis;
    Ennemy tom;
    Control control;
    ShootList shootList;
    ItemList itemList;
    Boolean gameStarted;
    int time;

    public static void main(String[] args) {
        PApplet.main("UlisJeu");
    }


    @Override
    public void settings() {
        //setSize(1600, 900);
        fullScreen();
        this.screen = new Screen(this);
        this.backGround = new BackGround(this, screen);
        this.itemList = new ItemList(this, screen, backGround);
        this.tom = new Ennemy(this, ulis, screen);
        this.shootList = new ShootList(this, screen, itemList);
        this.ulis = new Player(this, shootList, screen);
        this.control = new Control(ulis);

        time = 0;
        gameStarted = false;
    }

    @Override
    public void draw() {

        if (!gameStarted) {

        }

        if (gameStarted) {
            screen.draw();

            if (millis() > time + 500) {
                //tom.move();
                time += 500;
            }
            shootList.update();
        }

    }

    private void gameStart() {
        noCursor();
        background(0);
        backGround.set(0);
        itemList.set();
        ulis.spawn();
        gameStarted = true;
    }

    @Override
    public void keyTyped() {
        if (key == 'p') gameStart();

        control.treat(key);
    }
}
