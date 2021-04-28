package src;


public class Snake implements Elements{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private int[] positionX;
    private int[] positionY;


    public int[] getPositionX() {
        return positionX;
    }

    public int[] getPositionY() {
        return positionY;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setPositionX(int[] positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int[] positionY) {
        this.positionY = positionY;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void move(){

    }
}