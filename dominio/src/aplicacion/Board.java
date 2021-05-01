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
    int score = 0;
    public Board () {
        length = 10;
        width = 10;
        elements = new Element[length][width];
        Random r = new Random();
        snakes = new Snake[1];
        snakes[0] = new Snake(r.nextInt(length),r.nextInt(width),this);
        generateFruit();
    }
    public Board (int length,int width){
        elements = new Element[length][width];
        Random r = new Random();
        snakes = new Snake[1];
        snakes[0] = new Snake(r.nextInt(length),r.nextInt(width),this);
        generateFruit();
    }

    public void turnS (){
        snakes[0].move();
        int tempScore = score;
        setScore();
        if (this.score > tempScore){
            generateFruit();
        }
    }

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

    public void setScore(){
        score = snakes[0].getScore() - 3;
    }

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

    public void changeElementPos(Element element, int[] to){
        try {
            int[] from = element.getPosition();
            elements[to[0]][to[1]] = element;
            element.setPos(to);
            deleteElement(from);
        }
        catch (ArrayIndexOutOfBoundsException ignored){}
    }

    public void deleteElement(int[] pos){
        elements[pos[0]][pos[1]] = null;
    }

    public void addSnakePart(int y, int x){
        SnakePart snakePart = new SnakePart(y,x);
        elements[y][x] = snakePart;
    }

    public Element getElement(int y, int x){return elements[y][x];}

    public boolean isFruit(int y, int x){ return elements[y][x] instanceof Fruit; }

    public Color getFruitColor(int y, int x){ return elements[y][x].getColor(); }


    public boolean isSnake(int y, int x){ return elements[y][x] instanceof SnakePart; }

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

    public int getScore() {
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