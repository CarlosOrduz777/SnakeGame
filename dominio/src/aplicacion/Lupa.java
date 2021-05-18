package aplicacion;

import java.util.Random;

public class Lupa extends Surprise{
    public Lupa(int x, int y) {
        super(x, y);
    }

    @Override
    public void use(Snake snake) {
        snake.getOtherSnake().setAllowToeat(false);
    }

    @Override
    public String getName() {
        return "Lupa";
    }

    public void eaten(Snake snake){
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }

}
