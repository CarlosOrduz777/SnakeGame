package aplicacion;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class IncreaseVelocityArrow extends Surprise {

    public IncreaseVelocityArrow(int x, int y) {
        super(x, y);
    }

    public void use(Snake snake) {
        snake.getOtherSnake().setVelocity(snake.getOtherSnake().getVelocity()*0.75);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                snake.getOtherSnake().setVelocity(snake.getOtherSnake().getLastVelocity());
            }
        };
        timer.schedule(task,5000);
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
