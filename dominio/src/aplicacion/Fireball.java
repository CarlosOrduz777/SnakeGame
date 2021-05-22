package aplicacion;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que crea el objeto fireball, que se mueve en una direccion y si choca con algo tiene distintos
 * comportamientos
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Fireball implements Element, java.io.Serializable{
    private int y,x;
    private String name = "fb";
    private Board board;
    private Snake snake;
    private Color color;
    private Timer timer;

    /**
     * Metodo constructor de la bola de fuego, que determina su dirección, y posicion en un tablero de juego
     * @param y posicion en y en donde aparecera la bola de fuego
     * @param x posicion en x en donde aparecera la bola de fuego
     * @param board Tablero en donde aparecera la bola de fuego
     * @param snake Serpiente que aparecio la bola de fuego
     */
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

    /**
     * metodo que se ejecuta al ser comido por una serpiente
     * @param snake serpiente que comio la bola de fuego
     * @throws SnakeException vota la excepcion game over que es el fin del juego
     */
    @Override
    public void eaten(Snake snake) throws SnakeException {
        throw new SnakeException(SnakeException.GAME_OVER);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * metodo que hace que la bola de fuego se mueva en una direccion
     * @param direction direccion hacia la cual se va a mover la bola de fuego
     */
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

    /**
     * metodo que verifica que hay delante de la bola de fuego y dependiendo de esto da como resultado un comportamiento
     * @param inFront posicion al frente de la bola de fuego
     * @param fireball posicion de la bola de fuego
     */
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
