package aplicacion;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class IncreaseVelocityArrow extends Surprise {

    public IncreaseVelocityArrow(int x, int y) {
        super(x, y);
    }

    public void use(Snake snake) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                snake.getOtherSnake().setVelocity(1.0);
                snake.getBoard().pause();
                snake.getBoard().resume();
            }
        };
        timer.schedule(task,5000);
        snake.getOtherSnake().setVelocity(snake.getOtherSnake().getVelocity()*0.5);
    }

    @Override
    public String getName() {
        return "Increase Velocity Arrow";
    }

    public void eaten(Snake snake){
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }
}
