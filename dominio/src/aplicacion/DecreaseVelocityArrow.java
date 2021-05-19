package aplicacion;

import java.util.Timer;
import java.util.TimerTask;

public class DecreaseVelocityArrow extends Surprise{
    public DecreaseVelocityArrow(int x, int y) {
        super(x, y);
    }
    /**
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
        snake.getOtherSnake().setVelocity(snake.getOtherSnake().getVelocity()*1.5);
    }
     **/

    @Override
    public String getName() {
        return "Decrease Velocity Arrow";
    }

    public void eaten(Snake snake){
        super.eaten(snake);
        snake.setSurpriseName(getName());
    }

    @Override
    public void use(Snake snake) {

    }
}
