package aplicacion;

import java.awt.*;

/**
 * Clase que construye una sorpresa firestar, que al ser recogida le da el poder a la serpiente de lanzar una bola de fuego
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class FireStar extends Surprise{


    public FireStar(int y, int x) {
        super(y, x);
    }

    /**
     * Nos permite que al ser usado por una serpiente se construya una bola de fuego
     * @param snake serpiente que utiliza la firestar
     */
    @Override
    public void use(Snake snake) {
        int[] pos = snake.getHeadPos();
        Fireball fireball = new Fireball(pos[0],pos[1],snake);
        snake.getBoard().addElement(pos[0],pos[1],fireball);
    }

    @Override
    public String getName() {
        return "Fire Star";
    }

    public void eaten(Snake snake) {
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }
}
