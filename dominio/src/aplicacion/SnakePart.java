package aplicacion;

import java.awt.*;

public class SnakePart implements Element {
    String name = "s";
    Color color = Color.GREEN;
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

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void eaten(Board board, Snake snake) {

    }
}
