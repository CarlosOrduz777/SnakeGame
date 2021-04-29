package aplicacion;

public abstract class Food implements Element{
    private int x;
    private int y;
    
    public Food(int y, int x){
        this.x = x;
        this.y = y;
    }

    @Override
    public int[] getPosition() {
        int[] pos;
        pos = new int[]{y, x};
        return pos;
    }

    @Override
    public void setPos(int[] to) {
        y = to[0];
        x = to[1];
    }
}