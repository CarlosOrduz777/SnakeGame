package aplicacion;

import java.awt.*;

public abstract class Surprise implements Element{
    private int timeDuration;
    private int x;
    private int y;

    public Surprise(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getTimeDuration(){
        return this.timeDuration;
    }
    public void setTimeDuration(int timeDuration){
        this.timeDuration = timeDuration;
    }

    public abstract void eaten(Board board, Snake[] snakes);

    @Override
    public int[] getPosition() {
        return new int[0];
    }

    @Override
    public String getName() {
        return Element.super.getName();
    }

    @Override
    public void setPos(int[] to) {
        Element.super.setPos(to);
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }
}
