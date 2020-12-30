public class Control{
    Player ulis;
    char key;
    public Control(Player ulis) {
        this.ulis = ulis;
        this.key = '*';
    }

    public void treat(char key) {
       this.key = key;
    }
    public void stop() {
        key = '*';
    }

    public void update(){
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
            stop();
        }
    }
}
