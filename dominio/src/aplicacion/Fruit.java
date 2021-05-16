package aplicacion;

import java.awt.*;
import java.util.Random;

/**
 * Clase correspondiente a las frutas, alimento que al consumirse aumentan el tamaño de la serpiente en 1, y si la fruta
 * es del mismo color que la serpiente aumenta el tamaño en 2.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Fruit extends Food{
    String name = "f";
    Color color;

    /**
     * Constructor del objeto fruta que se crea con un color aleatorio de entre los 11 colores definidos
     * @param y posicion en y de la fruta
     * @param x posicion en x de la fruta
     */
    public Fruit(int y, int x) {
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
    public void eaten(Board board, Snake snake) {

    }

    //Should be started with a random color
}
