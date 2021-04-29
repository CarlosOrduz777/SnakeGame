package aplicacion;

import java.awt.*;

public class Fruit extends Food{
    String name = "f";
    Color color = Color.magenta;

    public Fruit(int y, int x) {
        super(y,x);
    }

    @Override
    public String getName() {
        return name;
    }

    //Should be started with a random color
}
