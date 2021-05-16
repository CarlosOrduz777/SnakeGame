package aplicacion;

import java.awt.*;

/**
 * Clase que construye el alimento veneno, este alimento al ser consumido finaliza el juego.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Poison extends Food {
    String name = "p";
    Color color;

    /**
     * Constructor del veneno que le asigna un color predeterminado
     * @param y posicion en y del veneno
     * @param x posicion en x del veneno
     */
    public Poison(int y, int x) {
        super(y,x);
        color = new Color(153, 204, 0);
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
