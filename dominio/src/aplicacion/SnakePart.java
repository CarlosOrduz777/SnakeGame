package aplicacion;

import java.awt.*;

/**
 * Clase que construye un elemento de tipo snake part
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class SnakePart implements Element {
    String name = "s";
    Color color = Color.GREEN;
    private int x;
    private int y;
    private int position;

    /**
     * Constructor del elemento snakePart
     * @param y posicion y en el tablero
     * @param x posicion x en el tablero
     */
    public SnakePart(int y, int x, int position){
        this.x = x;
        this.y = y;
        this.position = position;
    }

    /**
     * Retorna la posicion del elemento
     * @return posicion del elemento de la forma {y,x}
     */
    @Override
    public int[] getPosition() {
        int[] pos;
        pos = new int[]{y, x};
        return pos;
    }

    /**
     * Permite establecer la posición del elemento
     * @param to posicion de la forma {y,x}
     */
    @Override
    public void setPos(int[] to) {
        y = to[0];
        x = to[1];
    }

    /**
     * Retorna el nombre del elemento
     * @return string con el nombre del elemento
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retorna el color del elemento
     * @return color del elemento
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Nos permite asignar un color a un elemento
     * @param color color que deseamos asignarle al elemento
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void eaten( Snake snake) throws SnakeException {
        throw new SnakeException(SnakeException.GAME_OVER);

    }

    public int getIndex(){
        return position;
    }

}
