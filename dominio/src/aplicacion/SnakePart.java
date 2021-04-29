package aplicacion;

public class SnakePart implements Element {
    String name = "s";
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private int x;
    private int y;

    public SnakePart(int y, int x){
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

    @Override
    public String getName() {
        return name;
    }
}
