package aplicacion;

import java.awt.*;

/**
 * Clase interfaz que define los comportamientos de los elementos en el tablero
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public interface Element {
    String name = " ";
    Color color = null;

    int[] getPosition();

    /**
     * retorna el nombre del elemento
     * @return String con el nombre del elemento
     */
    default String getName(){
        return name;
    }

    void setPos(int[] to);
    Color getColor();
    void setColor(Color color);
    void eaten(Board board , Snake snake);
}