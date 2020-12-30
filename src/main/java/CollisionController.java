import Exeptions.Colision;

public class CollisionController {
    private Colision colision;
    private int[] pixelArray;

    public CollisionController(int grillWidth, int grillHeight) {
        pixelArray = new int[grillHeight * grillWidth];
    }

    public void setPixelArray(int pixelIndex, int value) {
        this.pixelArray[pixelIndex] = value;
    }

    public void testForCollision(int pixelIndex, int newType) throws Colision {
    if(pixelArray[pixelIndex] == ElementType.WALL){
        colision = new Colision(ElementType.WALL);
        throw colision;
    }else if(pixelArray[pixelIndex] == ElementType.HALF_WALL && newType == ElementType.PLAYER){
        colision = new Colision(ElementType.WALL);
        throw colision;
    }else if(pixelArray[pixelIndex] == ElementType.DOOR && newType == ElementType.PLAYER){
        colision = new Colision(ElementType.DOOR);
        throw colision;
    }else if(pixelArray[pixelIndex] > 100 && newType == ElementType.BULLET){
        colision = new Colision(pixelArray[pixelIndex]);
        throw colision;
    }
    else pixelArray[pixelIndex] = newType;
}
}
