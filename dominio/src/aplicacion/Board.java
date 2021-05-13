package aplicacion;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Board {

    Snake[] snakes;
    int length;
    int width;
    Element[][] elements;
    boolean game = true;
    int[] score = {0,0};
    int foodOnScreen =0;
    private Timer timer;


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
            generateFood();
            generateFood();
        }
    }

    public Board (int length,int width){
        elements = new Element[length][width];
        Random r = new Random();
        snakes = new Snake[1];
        snakes[0] = new Snake(r.nextInt(length),r.nextInt(width),this);
        generateFood();
    }

    /**
     *
     */
    public void turnS (int players){
        if (players == 2) {
            snakes[1].tae(snakes[0].getDmg());
            snakes[0].tae(snakes[1].getDmg());
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
     * move the Snake to the direction we want
     * @param direction that we want move the Snake
     */
    public void move (char direction){
        if (direction == 'u'){
            snakes[0].setUp(true);
        }
        else if (direction == 'r'){
            snakes[0].setRight(true);
        }
        else if (direction == 'd'){
            snakes[0].setDown(true);
        }
        else if (direction == 'l'){
            snakes[0].setLeft(true);
        }
    }

    public void move2 (char direction){
        if (direction == 'u'){
            snakes[1].setUp(true);
        }
        else if (direction == 'r'){
            snakes[1].setRight(true);
        }
        else if (direction == 'd'){
            snakes[1].setDown(true);
        }
        else if (direction == 'l'){
            snakes[1].setLeft(true);
        }
    }


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
     * Generate an aleatory fruit in the board
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
            elements[y][x] = new Fruit(y, x);
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
     * change the position of one Element to a new position
     * @param element the Element that we want to move
     * @param to the position that we want put the Element
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
     * delete an Element from the elements board
     * @param pos position of the Element that we want to delete
     */
    public void deleteElement(int[] pos){
        if (elements[pos[0]][pos[1]] instanceof Food){
            foodOnScreen -= 1;
        }
        elements[pos[0]][pos[1]] = null;
    }

    /**
     * add new part in the Snake
     * @param y position y of the Snake
     * @param x position x of the Snake
     */
    public void addSnakePart(int y, int x){
        SnakePart snakePart = new SnakePart(y,x);
        elements[y][x] = snakePart;
    }

    public Element getElement(int y, int x){return elements[y][x];}

    public boolean isFruit(int y, int x){ return elements[y][x] instanceof Fruit; }

    public boolean isRainbowFruit(int y, int x){ return elements[y][x] instanceof RainbowFruit; }

    public boolean isCandy(int y, int x){ return elements[y][x] instanceof Candy; }

    public boolean isPoison(int y, int x){ return elements[y][x] instanceof Poison; }

    public boolean isSnake(int y, int x){ return elements[y][x] instanceof SnakePart; }

    /**
     * read the the actual board
     * @return a new board with diferent values
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

    public boolean getStatus(){
        return game;
    }

    public int[] getScore() {
        return score;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public void setSnakeColor(Color color,int snake) {
        --snake;
        snakes[snake].setColor(color);
    }

    public Color getSnakeColor() {
        return snakes[0].getColor();
    }
}