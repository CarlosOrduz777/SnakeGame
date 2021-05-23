package aplicacion;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public abstract class Surprise implements Element,java.io.Serializable{

    private int x;
    private int y;
    private Color color = new Color(179, 0, 71);

    public Surprise(int y, int x){
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

    /**
     * La sorpresa al ser consumida es asignada a la serpiente que la consumio
     * @param snake serpiente que consumio la sorpresa
     */
    @Override
    public void eaten(Snake snake) {
        snake.addSurpirse(this);
    }

    public abstract void use(Snake snake);

}
