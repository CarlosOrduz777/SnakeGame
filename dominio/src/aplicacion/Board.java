package aplicacion;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que construye y manipula un tablero correspondiente al juego SnOOPe, modificación del juego snake.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Board {

    Snake[] snakes;
    int length;
    int width;
    Element[][] elements;
    boolean game = true;
    int[] score = {0,0};
    int foodOnScreen =0;
    private Timer timer;

    /**
     * Constructor de la clase board, inicializa un tablero con un tamaño fijo
     * @param players es la cantidad de jugadores que van a jugar snake
     */
    public Board (int players) {
        length = 10;
        width = 10;
        elements = new Element[length][width];
        if(players ==1) {
            Random r = new Random();
            snakes = new Snake[1];
            snakes[0] = new Snake(r.nextInt(length), r.nextInt(width), this);
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
     * @param players la cantidad de jugadores que estan jugando snake.
     */
    public void turnS (int players){
        if (players == 2) {
            snakes[1].shorten(snakes[0].getDamage());
            snakes[0].shorten(snakes[1].getDamage());
            snakes[0].move();
            snakes[0].updateParts();
            snakes[1].move();
            snakes[1].updateParts();
            setScore(2);
            while (foodOnScreen < 2){
                generateFood();
            }
        }
        else {
            snakes[0].move();
            snakes[0].updateParts();
            setScore(1);
            if (foodOnScreen < 1) {
                generateFood();
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
            snakes[0].setUp();
        }
        else if (direction == 'r'){
            snakes[0].setRight();
        }
        else if (direction == 'd'){
            snakes[0].setDown();
        }
        else if (direction == 'l'){
            snakes[0].setLeft();
        }
    }

    /**
     * mueve la serpiente 2 en una dirección, arriba, abajo, izquierda y derecha.
     * @param direction la dirección a la cual se desea mover la serpiente dada por caracteres arriba 'u', abajo 'd'
     *                  izquierda 'l' y derecha 'r'.
     */
    public void move2 (char direction){
        if (direction == 'u'){
            snakes[1].setUp();
        }
        else if (direction == 'r'){
            snakes[1].setRight();
        }
        else if (direction == 'd'){
            snakes[1].setDown();
        }
        else if (direction == 'l'){
            snakes[1].setLeft();
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
        while (elements[y][x] instanceof SnakePart){
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
        timer = new Timer();
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
        timer.schedule(turno, 12000);
    }

    /**
     * Genera una sorpresa aleatoria cada cierto tiempo en el tablero
     */
    public void generateSurprise(){

    }

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
            foodOnScreen -= 1;
        }
        elements[pos[0]][pos[1]] = null;
    }

    /**
     * añade una parte de una serpiente en el tablero
     * @param y posicion en y en donde se desea añadir
     * @param x posicion en x en donde se desea añadir
     */
    public void addSnakePart(int y, int x){
        SnakePart snakePart = new SnakePart(y,x);
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
}