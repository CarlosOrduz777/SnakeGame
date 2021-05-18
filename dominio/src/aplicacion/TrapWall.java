package aplicacion;

import java.awt.*;
import java.util.Random;

public class TrapWall extends Surprise{
    public TrapWall(int x, int y) {
        super(x, y);
    }

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
