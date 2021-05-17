package aplicacion;

import java.awt.*;
import java.util.Random;
import java.util.TimerTask;

public abstract class Surprise implements Element{
    private int timeDuration;
    private int x;
    private int y;

    private Color color;


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
        //Element.super.setPos(to);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    public void generateAleatoryColor(){
        Random r = new Random();
        int n = r.nextInt(11);
        switch (n) {
            case 0 -> color = Color.BLUE;
            case 1 -> color = Color.CYAN;
            case 2 -> color = Color.white;
            case 3 -> color = Color.GRAY;
            case 4 -> color = Color.GREEN;
            case 5 -> color = Color.LIGHT_GRAY;
            case 6 -> color = Color.MAGENTA;
            case 7 -> color = Color.ORANGE;
            case 8 -> color = Color.PINK;
            case 9 -> color = Color.RED;
            default -> color = Color.WHITE;
        }

    }


}
