package aplicacion;

import java.awt.*;

public class Division extends Surprise{

    public Division(int x, int y) {
        super(x, y);
        setTimeDuration(30);
        setColor(new Color(102, 255, 255));
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {
        snake.setDamage(snake.getOtherSnake().getScore()/2);
    }

}
