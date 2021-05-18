package aplicacion;


public class Division extends Surprise{
    public Division(int x, int y) {
        super(x, y);
    }

    @Override
    public void use(Snake snake) {
        snake.getOtherSnake().shorten(snake.getOtherSnake().getScore()/2);
    }

    @Override
    public String getName() {
        return "Division";
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }
}
