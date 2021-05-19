package aplicacion;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que construye y manipula un tablero correspondiente al juego SnOOPe, modificación del juego snake.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Board implements java.io.Serializable{

    Snake[] snakes;
    int length;
    int width;
    Element[][] elements;
    boolean game = true;
    int[] score = {0,0};
    int foodOnScreen =0;
    int surpriseOnScreen =0;
    private Timer timer;
    private TimerTask task;
    private Timer timerFruit;
    private Timer timerSurprise;

    private int players;

    /**
     * Constructor de la clase board, inicializa un tablero con un tamaño fijo
     * @param players es la cantidad de jugadores que van a jugar snake
     */
    public Board (int players) {
        this.players = players;
        length = 10;
        width = 10;
        elements = new Element[length][width];
        if(players ==1) {
            Random r = new Random();
            snakes = new Snake[1];
            snakes[0] = new Snake(r.nextInt(length), r.nextInt(width), this);
            snakes[0].setOtherSnake(snakes[0]);
            generateFood();
        }else if(players == 2){
            snakes = new Snake[2];
            snakes[0] = new Snake(0,0,this);
            snakes[1] = new Snake(9,9,this);
            snakes[0].setOtherSnake(snakes[1]);
            snakes[1].setOtherSnake(snakes[0]);
            generateFood();
            generateFood();
        }
    }

    /**
     * Metodo que actualiza el tablero, mueve la serpiente y genera los elementos en un turno.
     */
    public void turnS () {
        if (players == 2) {
            snakes[1].shorten(snakes[0].getDamage());
            snakes[0].shorten(snakes[1].getDamage());//Acorta la serpiente
            snakes[0].updateParts();// Mira las partes pendiente de la serpiente y las añade de una en una
            snakes[1].updateParts();
            snakes[0].updateVelocity();
            snakes[1].updateVelocity();
            setScore(2);
            while (foodOnScreen < 2){
                generateFood();
            }
            if (surpriseOnScreen < 1){
                surpriseOnScreen += 1;
                Random r = new Random();
                int time = r.nextInt(10)+1;
                timerSurprise = new Timer();
                TimerTask turno = new TimerTask() {
                    @Override
                    public void run() {
                        generateSurprise();
                    }
                };
                timer.schedule(turno, time * 2000);
            }
        }
        else {
            snakes[0].shorten(snakes[0].getDamage());
            snakes[0].updateParts();
            snakes[0].updateVelocity();
            setScore(1);
            if (foodOnScreen < 1) {
                generateFood();
            }
            if (surpriseOnScreen < 1){
                surpriseOnScreen += 1;
                Random r = new Random();
                int time = r.nextInt(10)+1;
                timerSurprise = new Timer();
                TimerTask turno = new TimerTask() {
                    @Override
                    public void run() {
                        generateSurprise();
                    }
                };
                timer.schedule(turno, time * 2000);
            }
        }
    }

    /**
     * mueve la serpiente 1 en una dirección, arriba, abajo, izquierda y derecha.
     * @param direction la dirección a la cual se desea mover la serpiente dada por caracteres arriba 'u', abajo 'd'
     *                  izquierda 'l' y derecha 'r'.
     */
    public void move (char direction){
        if (direction == 'u'){
            if (!snakes[0].isDown()) {
                snakes[0].setUp();
            }
        }
        else if (direction == 'r'){
            if (!snakes[0].isLeft()){
                snakes[0].setRight();
            }
        }
        else if (direction == 'd'){
            if (!snakes[0].isUp()){
                snakes[0].setDown();
            }
        }
        else if (direction == 'l'){
            if (!snakes[0].isRight()){
                snakes[0].setLeft();
            }
        }
    }

    public void use(int player){
        if (player == 1){
            snakes[0].useSurprise();
        }
        else {
            snakes[player-1].useSurprise();
        }
    }

    /**
     * mueve la serpiente 2 en una dirección, arriba, abajo, izquierda y derecha.
     * @param direction la dirección a la cual se desea mover la serpiente dada por caracteres arriba 'u', abajo 'd'
     *                  izquierda 'l' y derecha 'r'.
     */
    public void move2 (char direction){
        if (direction == 'u'){
            if (!snakes[1].isDown()) {
                snakes[1].setUp();
            }
        }
        else if (direction == 'r'){
            if (!snakes[1].isLeft()){
                snakes[1].setRight();
            }
        }
        else if (direction == 'd'){
            if (!snakes[1].isUp()){
                snakes[1].setDown();
            }
        }
        else if (direction == 'l'){
            if (!snakes[1].isRight()){
                snakes[1].setLeft();
            }
        }
    }


    /**
     * metodo que establece los puntajes de los jugadores.
     * @param player numero de jugadores jugando snake.
     */
    public void setScore(int player){
        if (player == 2){
            score[0] = snakes[0].getScore();
            score[1] = snakes[1].getScore();
        }
        else {
            score[0] = snakes[0].getScore();
        }
    }

    /**
     * Genera una comida aleatoria en el tablero, esta comida puede ser una fruta, una fruta arcoiris, un dulce o un
     * veneno.
     */
    public void generateFood(){ //It can be changed to generate aplicacion.Food to implement the other kind of foods
        Random r = new Random();
        int y = r.nextInt(length);
        int x = r.nextInt(width);
        while (elements[y][x] != null){
            y = r.nextInt(length);
            x = r.nextInt(width);
        }
        Random r2 = new Random();
        int opt = r2.nextInt(4);
        if (opt == 0) {
            elements[y][x] = new Fruit(y,x);

        }
        else if (opt == 1){
            elements[y][x] = new RainbowFruit(y,x);
        }
        else if(opt == 2){
            elements[y][x] = new Candy(y,x);
        }
        else {
            elements[y][x] = new Poison(y,x);
        }
        foodOnScreen +=1;
        Timer timerFruit = new Timer();
        int finalY = y;
        int finalX = x;
        TimerTask turno = new TimerTask() {
            @Override
            public void run() {
                int[] pos = {finalY, finalX};
                if (!isSnake(finalY,finalX)){
                    deleteElement(pos);
                }
            }
        };
        timerFruit.schedule(turno, 12000);
    }

    public void generateSurprise(){
        Random r = new Random();
        int y = r.nextInt(length);
        int x = r.nextInt(width);
        while (elements[y][x] != null){
            y = r.nextInt(length);
            x = r.nextInt(width);
        }
        Random r2 = new Random();
        int opt = r2.nextInt(2);
        switch (opt) {
            //case 0 -> elements[y][x] = new Division(y, x);
            //case 1 -> elements[y][x] = new TrapWall(y, x);
            //case 2 -> elements[y][x] = new FireStar(y, x);
            //case 3 -> elements[y][x] = new Lupa(y,x);
            case 0 -> elements[y][x] = new IncreaseVelocityArrow(y,x);
            default -> elements[y][x] = new DecreaseVelocityArrow(y, x);
        }
    }

    /**
     * Genera una sorpresa aleatoria cada cierto tiempo en el tablero
     */

    /**
     * cambia la posición de un elemento en el tablero
     * @param element Elemento que se desea mover
     * @param to posición a la cual se va a mover el elementos
     */
    public void changeElementPos(Element element, int[] to){
        try {
            int[] from = element.getPosition();
            elements[to[0]][to[1]] = element;
            element.setPos(to);
            deleteElement(from);
        }
        catch (ArrayIndexOutOfBoundsException ignored){}
    }

    /**
     * elimina un elemento en el tablero
     * @param pos arreglo que contiene la posicion del elemento que se desea borrar del tablero en la forma de {y,x}
     */
    public void deleteElement(int[] pos){
        if (elements[pos[0]][pos[1]] instanceof Food){
            if (foodOnScreen > 0){
                foodOnScreen -= 1;
            }
        }
        if (elements[pos[0]][pos[1]] instanceof Surprise){
            if (surpriseOnScreen > 0){
                surpriseOnScreen -= 1;
            }
        }
        elements[pos[0]][pos[1]] = null;
    }

    /**
     * añade una parte de una serpiente en el tablero
     * @param y posicion en y en donde se desea añadir
     * @param x posicion en x en donde se desea añadir
     */
    public void addSnakePart(int y, int x,int position){
        SnakePart snakePart = new SnakePart(y,x,position);
        elements[y][x] = snakePart;
    }

    public Element getElement(int y, int x){return elements[y][x];}

    public boolean isSnake(int y, int x){ return elements[y][x] instanceof SnakePart; }

    /**
     * lee el tablero
     * @return retorna el tablero en forma de string.
     */
    public String[][] readBoard(){
        String[][] names = new String[length][width];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                if (elements[i][j] == null){
                    names[i][j] = "0";
                }
                else {
                    names[i][j] = elements[i][j].getName();
                }
            }
        }
        return names;
    }

    /**
     * Retorna el estatus del juego.
     * @return true si todavia no se pierde el juego, false si ya se perdió el juego.
     */
    public boolean getStatus(){
        return game;
    }

    /**
     * Retorna los puntajes del juego.
     * @return es un arreglo que contiene los puntajes de la siguiente forma {puntaje de jugador 1, puntaje de jugador 2}.
     */
    public int[] getScore() {
        return score;
    }

    /**
     * Define el estatus del juego.
     * @param game true si todavia no se pierde el juego, false si ya se perdió el juego.
     */
    public void setGame(boolean game) {
        this.game = game;
    }

    /**
     * Metodo que nos permite definir el color de la serpiente
     * @param color Color que queremos que sea la serpiente
     * @param snake Serpiente a la cual deseamos cambiarle el color
     */
    public void setSnakeColor(Color color,int snake) {
        --snake;
        snakes[snake].setColor(color);
    }

    public void addElement(int y, int x, Element element){
        elements[y][x] = element;
    }

    public String[] getSorpresa(){
        if (snakes.length < 2){
            return new String[]{snakes[0].getSurpriseName()};
        }
        else {
            return new String[]{snakes[0].getSurpriseName(), snakes[1].getSurpriseName()};
        }

    }

    public void pause(){
        for (Snake s: snakes){
            if (s != null){
                s.pause();
            }
        }
        if(timer!=null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
    public void resume(){
        for(Snake s: snakes){
            if (s != null){
                s.resume();
            }
        }
        runnable();
    }
    /**
     * Actualiza el estado de la serpiente constantemente teniendo en cuenta el refresco de la GUI
     */
    public void runnable (){
        snakes[0].runSnake();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                turnS();
            }
        };
        timer.schedule(task,0,1000);
    }

}