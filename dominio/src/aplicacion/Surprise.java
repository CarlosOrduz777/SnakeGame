package aplicacion;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public abstract class Surprise implements Element{

    private int x;
    private int y;
    private Color color = new Color(179, 0, 71);

    public Surprise(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int[] getPosition() {
        return new int[]{y, x};
    }


    @Override
    public void setPos(int[] to) {
        x = to[1];
        y = to[0];
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {
        snake.addSurpirse(this);
    }

    public abstract void use(Snake snake);

}
