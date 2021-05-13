package aplicacion;

import java.awt.*;

public class Poison extends Food {
    String name = "p";
    Color color;

    public Poison(int y, int x) {
        super(y,x);
        color = new Color(153, 204, 0);
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
