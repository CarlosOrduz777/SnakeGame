package aplicacion;

import java.awt.*;

/**
 * Clase interfaz que define los comportamientos de los elementos en el tablero
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public interface Element {

    int[] getPosition();
    String getName();
    void setPos(int[] to);
    Color getColor();
    void setColor(Color color);
    void eaten(Snake snake) throws SnakeException;
    void fireballCheck(Snake snake);
    void deleteElement(int[] pos, Board board);
}