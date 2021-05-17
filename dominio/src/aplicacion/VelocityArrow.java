package aplicacion;

public class VelocityArrow extends Surprise {

    public VelocityArrow(int x, int y) {
        super(x, y);
        setTimeDuration(15);
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {

    }
}
