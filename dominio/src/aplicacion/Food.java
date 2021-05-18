package aplicacion;

import java.awt.*;

/**
 * Clase que construye la clase de comida.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public abstract class Food implements Element,java.io.Serializable{
    private int x;
    private int y;

    /**
     * Constructor de comida.
     * @param y posición y en el tablero
     * @param x posición x en el tablero
     */
    public Food(int y, int x){
        this.x = x;
        this.y = y;
    }


    /**
     * Retorna la posición de la comida.
     * @return arreglo de la forma {y, x}.
     */
    @Override
    public int[] getPosition() {
        int[] pos;
        pos = new int[]{y, x};
        return pos;
    }

    /**
     * Define la posicion de la comida a partir de un arreglo
     * @param to arreglo de la forma {y, x}.
     */
    @Override
    public void setPos(int[] to) {
        y = to[0];
        x = to[1];
    }


    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {

    }
}