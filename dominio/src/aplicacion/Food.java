package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Food implements Element{
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

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void eaten(Board board, Snake snake) {

    }
}