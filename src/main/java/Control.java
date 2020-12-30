public class Control{
    Player ulis;
    public Control(Player ulis) {
        this.ulis = ulis;
    }

    public void treat(char key) {
        if(key == 'z'){
            ulis.move("front");
        }
        if(key == 'q'){
            ulis.move("left");
        }
        if(key == 's'){
            ulis.move("back");
        }
        if(key == 'd'){
            ulis.move("right");
        }
        if(key == ' '){
            ulis.shoot();
        }
    }
}
