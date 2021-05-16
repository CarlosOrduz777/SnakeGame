package aplicacion;

import java.awt.*;
import java.util.Random;

/**
 * Clase correspondiente al alimento Candy, alimento que al consumirse quita puntos al rival, si es del mismo color que
 * la serpiente que lo consume quita dos puntos, de lo contrario quita uno.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Candy extends Fruit{
    String name = "c";
    Color color;

    /**
     * Clase constructora del dulce
     * @param y posición en y en el tablero.
     * @param x posición en x en el tablero.
     */
    public Candy(int y, int x) {
        super(y,x);
        color = new Color(255, 153, 255);
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
}
