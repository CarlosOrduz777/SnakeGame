package aplicacion;

import java.awt.*;

public interface Element {
    String name = " ";
    Color color = null;

    int[] getPosition();
    default String getName(){
        return name;
    }
    default void setPos(int[] to){}

    Color getColor();
    void setColor(Color color);
}