package aplicacion;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Board {

    Snake[] snakes;
    int length;
    int width;
    Element[][] elements;
    boolean game = true;
    int[] score = {0,0};


    public Board (int players) {
        length = 10;
        width = 10;
        elements = new Element[length][width];
        if(players ==1) {
            Random r = new Random();
            snakes = new Snake[1];
            snakes[0] = new Snake(r.nextInt(length), r.nextInt(width), this);
            generateFruit();
        }else if(players == 2){
            snakes = new Snake[2];
            snakes[0] = new Snake(0,0,this);
            snakes[1] = new Snake(9,9,this);
            generateFruit();
            generateFruit();
        }
    }

    public Board (int length,int width){
        elements = new Element[length][width];
        Random r = new Random();
        snakes = new Snake[1];
        snakes[0] = new Snake(r.nextInt(length),r.nextInt(width),this);
        generateFruit();
    }

    /**
     *
     */
    public void turnS (int players){
        if (players == 2) {
            snakes[0].move();
            snakes[1].move();
            int tempScore1 = score[0];
            int tempScore2 = score[1];
            setScore(2);
            if (this.score[0] > tempScore1) {
                generateFruit();
            }
            if (this.score[1] > tempScore2) {
                generateFruit();
            }
        }
        else {
            snakes[0].move();
            int tempScore1 = score[0];
            setScore(1);
            if (this.score[0] > tempScore1) {
                generateFruit();
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
            score[0] = snakes[0].getScore() - 3;
            score[1] = snakes[1].getScore() - 3;
        }
        else {
            score[0] = snakes[0].getScore() - 3;
        }
    }

    /**
     * Generate an aleatory fruit in the board
     */
    public void generateFruit(){ //It can be changed to generate aplicacion.Food to implement the other kind of foods
        Random r = new Random();
        int y = r.nextInt(length);
        int x = r.nextInt(width);
        while (elements[y][x] instanceof SnakePart){
            y = r.nextInt(length);
            x = r.nextInt(width);
        }
        elements[y][x] = new Fruit(y,x);
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

    public void setSnakeColor(Color color) {
        snakes[0].setColor(color);
    }

    public Color getSnakeColor() {
        return snakes[0].getColor();
    }
}