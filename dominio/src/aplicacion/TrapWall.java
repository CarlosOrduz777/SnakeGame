package aplicacion;

import java.awt.*;

public class TrapWall extends Surprise{

    public TrapWall(int x, int y) {
        super(x, y);
        setColor(new Color(166, 166, 166));
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {
        throw new SnakeException(SnakeException.GAME_OVER);
    }
}
