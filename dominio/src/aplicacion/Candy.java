package aplicacion;

import java.awt.*;
import java.util.Random;

public class Candy extends Fruit{
    String name = "c";
    Color color;

    public Candy(int y, int x) {
        super(y,x);
        color = new Color(255, 153, 255);
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
}
