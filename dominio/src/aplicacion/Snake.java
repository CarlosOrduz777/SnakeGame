package aplicacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que construye y manipula una serpiente del juego SnOOPe, modificacion del juego snake
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Snake implements java.io.Serializable{

    private final Board board;
    private SnakePart head;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private final ArrayList<SnakePart> parts = new ArrayList<>();
    private Color color = Color.green;
    private int pendingParts = 0;
    private int[] tail;
    private int score = 0;
    private int temporaryScore = 0;
    private int damage = 0;
    private double velocity;
    private Surprise surprise;
    private Snake otherSnake;
    private String surpriseName = "Ninguna";
    private boolean allowToeat = true;
    private Timer timer;
    private TimerTask timerTask;
    private double lastVelocity;

    /**
     * Constructor del objeto serpiente en el tablero y con 3 de longitud, si la serpiente se genera en una posicion
     *  x = 9, esta se generara mirando hacia la izquierda, de lo contrario se genera mirando a la derecha.
     * @param y posicion en y en la cual se quiere generar la serpiente
     * @param x posicion en x en la cual se quiere generar la serpiente
     * @param board tablero correspondiente al juego en donde va a estar la serpiente
     */
    public Snake(int y,int x, Board board){
        this.board = board;
        this.velocity = 1;
        this.lastVelocity = 1;
        if(x ==9){
            for (int x1 = x-2; x1 <= x; x1++) {
                board.addSnakePart(y, x1,parts.size());
                parts.add((SnakePart) board.getElement(y, x1));
            }
            head = parts.get(0);
            head.setColor(new Color(0, 153, 51));
            setLeft();
        }else {
            if (x < 2) {
                //throw the snake need at least two spaces to the left to start
                x = 2;
            }
            if (x == board.width) {
                x = x - 2;
            }
            for (int x1 = x; x1 >= (x - 2); x1--) {
                board.addSnakePart(y, x1,parts.size());
                parts.add((SnakePart) board.getElement(y, x1));
            }
            head = parts.get(0);
            head.setColor(new Color(0, 153, 51));
            setRight();
        }
        tail = parts.get(parts.size()-1).getPosition();
    }

    public void setOtherSnake(Snake otherSnake) {
        this.otherSnake = otherSnake;
    }

    public Snake getOtherSnake() {
        return otherSnake;
    }

    /**
     * Retorna el puntaje de la serpiente
     * @return puntaje de la serpiente
     */
    public int getScore(){
        return score;
    }

    /**
     * Actualiza el score a un nuevo valor
     * @param score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * @return si la serpiente mira hacia abajo true de lo contrario false
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @return si la serpiente mira hacia la izquierda true de lo contrario false
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return si la serpiente mira hacia la derecha true de lo contrario false
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return si la serpiente mira hacia arriba true de lo contrario false
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Define si la serpiente va a mirar hacia abajo
     */
    public void setDown() {
        this.down = true;
        this.left = false;
        this.right = false;
        this.up = false;
    }

    /**
     * Define si la serpiente va a mirar hacia la izquierda
     */
    public void setLeft() {
        this.down = false;
        this.left = true;
        this.right = false;
        this.up = false;
    }

    /**
     * Define si la serpiente va a mirar hacia la derecha
     */
    public void setRight() {
        this.down = false;
        this.left = false;
        this.right = true;
        this.up = false;
    }

    /**
     * Define si la serpiente va a mirar hacia abajo
     */
    public void setUp() {
        this.down = false;
        this.left = false;
        this.right = false;
        this.up = true;
    }

    /**
     * Mueve la serpiente hacia la direccion que esta dirigida
     */
    public void move() {
        try {
            if (isDown()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0] + 1;
                to[1] = from[1];
                check(to,from);
            }
            if (isLeft()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] - 1;
                check(to,from);
            }
            if (isRight()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] + 1;
                check(to,from);
            }
            if (isUp()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0] - 1;
                to[1] = from[1];
                check(to,from);
            }
        }
        catch (ArrayIndexOutOfBoundsException | SnakeException e){
            board.setGame(false);
        }
    }


    /**
     * Se encarga de eliminar un elemento del tablero y agregar la cantidad de partes pendientes por generar
     * @param to elemento que se va a eliminar
     * @param times cantidad de partes pendientes de la serpiente
     */
    public void eat(int[] to, int times){
        tail = parts.get(parts.size()-1).getPosition();
        board.deleteElement(to);
        pendingParts += times;
    }

    /**
     * Reduce el tamaño de la serpiente
     * @param times veces que se quiere reducir el tamaño de la serpiente
     */
    public void shorten(int times){
        while (times >0){
            tail = parts.get(parts.size() - 1).getPosition();
            board.deleteElement(tail);
            times--;
        }
    }

    /**
     * Se encarga de mover las partes de una serpiente de tal manera que cada parte este en donde estuvo la parte siguiente
     * en el turno anterior
     * @param to posicion a la que se desea mover la cabeza
     * @param from posicion en donde estuvo la cabeza
     */
    private void moveParts(int[] to, int[] from){
        if (board.getElement(to[0],to[1]) != null){
            board.deleteElement(to);
        }
        board.changeElementPos(head, to);
        to[0] = from[0];
        to[1] = from[1];
        for (int i = 1; i < parts.size(); i++) {
            SnakePart part = parts.get(i);
            from = part.getPosition();
            board.changeElementPos(part, to);
            to[0] = from[0];
            to[1] = from[1];
        }
    }

    /**
     * Metodo que verifica que elemento se encuentra adelante de la cabeza de la serpiente y a partir de eso, que
     * comportamiento va a tener la serpiente al toparse con el.
     * @param inFront elemento que vamos a analizar
     * @param snakeHead posicion de la cabeza de la serpiente
     */
    private void check (int[] inFront,int[] snakeHead) throws SnakeException {
        if(board.getElement(inFront[0],inFront[1])!= null) {
            board.getElement(inFront[0], inFront[1]).eaten(this);
        }
        moveParts(inFront, snakeHead);
    }

    /**
     * Metodo que añade a la serpiente una parte que este pendiente
     */
    public void updateParts(){
        if (pendingParts > 0) {
            board.addSnakePart(tail[0], tail[1],parts.size());
            parts.add((SnakePart) board.getElement(tail[0], tail[1]));
            setColor(color);
            pendingParts--;
        }
    }

    /**
     * Metodo que nos permite asignarle un color a cada parte de la serpiente exceptuando la cabeza
     * @param c Color que deseamos asignar
     */
    public void setColor(Color c){
        color = c;
        for (SnakePart snakePart: parts){
            snakePart.setColor(c);
        }
        head.setColor(new Color(0, 153, 51));
    }

    /**
     * Metodo que nos devuelve el color del cuerpo de la serpiente
     * @return
     */
    public Color getColor(){
        return color;
    }

    /**
     * Metodo que nos retorna el daño que se debe realizar a la otra serpiente
     * @return daño que se quiere realizar a la otra serpiente
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Actualiza el damage
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Board getBoard() {
        return board;
    }

    public void addSurpirse(Surprise surprise){
        this.surprise = surprise;
    }

    public void setPendingParts(int pendingParts) {
        this.pendingParts = pendingParts;
    }

    public char getDirection(){
        if(isLeft()){
            return 'l';
        }
        else if (isRight()){
            return 'r';
        }
        else if (isDown()){
            return 'd';
        }
        else {
            return 'u';
        }
    }

    public void useSurprise(){
        if (surprise != null){
            surprise.use(this);
            surprise = null;
            surpriseName = "Ninguna";
        }
    }

    public int[] getHeadPos(){
        return head.getPosition();
    }

    public void setSurpriseName(String surpriseName) {
        this.surpriseName = surpriseName;
    }

    public String getSurpriseName(){
        return surpriseName;
    }

    public void setAllowToeat(boolean allowToeat) {
        this.allowToeat = allowToeat;
    }
    public boolean getAllowToEat(){
        return this.allowToeat;
    }

    /**
     * Construye los timers para que la serpiente se mueva cada cierto tiempo
     */
    public void runSnake(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        timer.schedule(timerTask,0, 1000);
    }
    public void updateVelocity(){
    }

    public void pause(){
        if(timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }

    }
    public void resume(){
        runSnake();
    }


    public void setVelocity(double velocity) {
        this.lastVelocity = this.velocity;
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getLastVelocity() {
        return lastVelocity;
    }
}