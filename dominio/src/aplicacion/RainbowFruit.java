package aplicacion;

import java.awt.*;
import java.util.Random;

public class RainbowFruit extends Food {
    String name = "rf";
    Color color;

    public RainbowFruit(int y, int x) {
        super(y,x);
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

    @Override
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public void nextColor() {
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
