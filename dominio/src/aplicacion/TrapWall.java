package aplicacion;

import java.awt.*;
import java.util.Random;

/**
 * Clase que construlle la sorpresa trap wall
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class TrapWall extends Surprise{
    public TrapWall(int x, int y) {
        super(x, y);
    }

    /**
     * Al ser utilizada construlle en una posición aleatoria un muro
     * @param snake serpiente que utiliza la sorpresa
     */
    @Override
    public void use(Snake snake) {
        Random r = new Random();
        int y = r.nextInt(10);
        int x = r.nextInt(10);
        while (snake.getBoard().getElement(y,x) != null){
            y = r.nextInt(10);
            x = r.nextInt(10);
        }
        Wall wall = new Wall(y,x);
        snake.getBoard().addElement(y,x,wall);
    }

    @Override
    public String getName() {
        return "Trap Wall";
    }

    public void eaten(Snake snake){
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }
}
