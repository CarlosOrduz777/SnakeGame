package aplicacion;

import java.awt.*;

public interface Element {
    String name = " ";

    int[] getPosition();
    default String getName(){
        return name;
    }
    default void setPos(int[] to){}

    Color getColor();
}