package aplicacion;

import java.awt.*;

public class FireStar extends Surprise{

    public FireStar(int x, int y) {
        super(x, y);

    }

    @Override
    public void use(Snake snake) {
        int[] pos = snake.getHeadPos();
        Fireball fireball = new Fireball(pos[0],pos[1],snake.getBoard(),snake);
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
