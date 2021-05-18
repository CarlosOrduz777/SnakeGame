package aplicacion;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fireball implements Element{
    private int y,x;
    private String name = "fb";
    private Board board;
    private Snake snake;
    private Color color;
    private Timer timer;

    public Fireball(int y, int x, Board board, Snake snake){
        char direction =snake.getDirection();
        if (direction == 'd') {
            y ++;
        }
        if (direction == 'l') {
            x --;
        }
        if (direction == 'r') {
            x ++;
        }
        if (direction == 'u') {
            y --;
        }
        this.y = y;
        this.x = x;
        this.board = board;
        this.snake = snake;
        setColor(new Color(255, 102, 0));
        timer = new Timer();
        TimerTask turno = new TimerTask() {
            @Override
            public void run() {
                    move(direction);
                }
            };
        timer.schedule(turno, 0,1000);
    }

    @Override
    public int[] getPosition() {
        return new int[]{y, x};
    }

    @Override
    public void setPos(int[] to) {
        x = to[1];
        y = to[0];
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void eaten(Snake snake) throws SnakeException {
        throw new SnakeException(SnakeException.GAME_OVER);
    }

    @Override
    public String getName() {
        return name;
    }

    public void move(char direction) {
        try {
            if (direction == 'd') {
                int[] from = getPosition();
                int[] to = new int[2];
                to[0] = from[0] + 1;
                to[1] = from[1];
                check(to,from);
            }
            if (direction == 'l') {
                int[] from = getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] - 1;
                check(to,from);
            }
            if (direction == 'r') {
                int[] from = getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] + 1;
                check(to,from);
            }
            if (direction == 'u') {
                int[] from = getPosition();
                int[] to = new int[2];
                to[0] = from[0] - 1;
                to[1] = from[1];
                check(to,from);
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored){
            board.deleteElement(getPosition());
            timer.cancel();
            timer.purge();
        }
    }

    public void check(int[] inFront, int[] fireball){
        if(board.getElement(inFront[0],inFront[1])!= null) {
            if(board.getElement(inFront[0], inFront[1]) instanceof Food){
                board.deleteElement(inFront);
                board.deleteElement(fireball);
                timer.cancel();
                timer.purge();
            }
            else if(board.getElement(inFront[0], inFront[1]) instanceof Wall){
                board.deleteElement(inFront);
                snake.setPendingParts(5);
                snake.setScore(snake.getScore()+5);
                board.deleteElement(fireball);
                timer.cancel();
                timer.purge();
            }
            else if(board.getElement(inFront[0], inFront[1]) instanceof SnakePart){//this only analizes if the other snake is hit
                snake.setDamage(snake.getOtherSnake().getScore()-((SnakePart) board.getElement(inFront[0], inFront[1])).getIndex());
                board.deleteElement(fireball);
                timer.cancel();
                timer.purge();
            }
        }
        else {
            board.changeElementPos(this, inFront);
        }
    }


}
