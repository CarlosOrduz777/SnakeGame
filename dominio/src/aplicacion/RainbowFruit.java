package aplicacion;

import java.awt.*;
import java.util.Random;

/**
 * Clase que construye el alimento rainbow fruit, este alimento da 3 puntos al ser consumido, ademas de cambiar de color
 * en cada turno.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class RainbowFruit extends Food {
    String name = "rf";
    Color color;

    /**
     * Constructor del objeto fruta arcoiris que se crea con un color aleatorio de entre los 11 colores definidos
     * @param y posicion en y de la fruta
     * @param x posicion en x de la fruta
     */
    public RainbowFruit(int y, int x) {
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

    /**
     * Metodo que genera otro color aleatorio
     */
    public void nextColor() {
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

    @Override
    public void eaten(Snake snake) {
        snake.eat(getPosition(),3);
        snake.setScore(snake.getScore() + 3);
    }
}
