package aplicacion;


public class Division extends Surprise{
    public Division(int y, int x) {
        super(y, x);
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
    public void eaten(Snake snake) {
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }
}
