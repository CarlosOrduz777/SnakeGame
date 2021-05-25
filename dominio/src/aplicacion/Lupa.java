package aplicacion;

public class Lupa extends Surprise{
    public Lupa(int y, int x) {
        super(y, x);
    }

    /**
     * Al usarse esta sorpresa impide que otra serpiente coma
     * @param snake
     */
    @Override
    public void use(Snake snake) {
        snake.getOtherSnake().setAllowToEat(false);
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
