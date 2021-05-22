package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {

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
    private int dmg = 0;

    public Snake(int y,int x, Board board){
        this.board = board;
        if(x ==9 && y==9){
            for (int x1 = x-2; x1 <= x; x1++) {
                board.addSnakePart(y, x1);
                parts.add((SnakePart) board.getElement(y, x1));
            }
            head = parts.get(0);
            head.setColor(new Color(0, 153, 51));
            setLeft(true);
        }else {
            if (x < 2) {
                //throw the snake need at least two spaces to the left to start
                x = 2;
            }
            if (x == board.width) {
                x = x - 2;
            }
            for (int x1 = x; x1 >= (x - 2); x1--) {
                board.addSnakePart(y, x1);
                parts.add((SnakePart) board.getElement(y, x1));
            }
            head = parts.get(0);
            head.setColor(new Color(0, 153, 51));
            setRight(true);
        }
        move();
    }

    public int getScore(){
        return score;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public void setDown(boolean down) {
        this.down = down;
        this.left = false;
        this.right = false;
        this.up = false;
    }

    public void setLeft(boolean left) {
        this.down = false;
        this.left = left;
        this.right = false;
        this.up = false;
    }

    public void setRight(boolean right) {
        this.down = false;
        this.left = false;
        this.right = right;
        this.up = false;
    }

    public void setUp(boolean up) {
        this.down = false;
        this.left = false;
        this.right = false;
        this.up = up;
    }

    /**
     * Move the Snake according to the current direction
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
        catch (ArrayIndexOutOfBoundsException e){
            board.setGame(false);
        }
    }
    private void eat(int[] to, int times){
        tail = parts.get(parts.size()-1).getPosition();
        board.deleteElement(to);
        pendingParts += times;
    }
    public void tae(int times){
        while (times >0){
            tail = parts.get(parts.size() - 1).getPosition();
            board.deleteElement(tail);
            times--;
        }
    }
    private void moveParts(int[] to, int[] from){
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
    private void check (int[] to,int[] from){
        if (board.isFruit(to[0], to[1])) {
            if (board.getElement(to[0],to[1]).getColor().getRGB() == color.getRGB()){
                eat(to,2);
                score += 2;
            }
            else {
                eat(to,1);
                score += 1;
            }
        }
        else if(board.isRainbowFruit(to[0],to[1])){
            eat(to,3);
            score += 3;
        }
        else if(board.isCandy(to[0],to[1])) {
            if (board.getElement(to[0], to[1]).getColor().getRGB() == color.getRGB()) {
                dmg += 2;
                board.deleteElement(to);
            } else {
                dmg += 1;
                board.deleteElement(to);
            }
        }else if(board.isPoison(to[0],to[1])){
            throw new ArrayIndexOutOfBoundsException();
        }
        else if (board.isSnake(to[0],to[1])){
            throw new ArrayIndexOutOfBoundsException();
        }
        moveParts(to,from);
    }
    public void updateParts(){
        if (pendingParts > 0) {
            board.addSnakePart(tail[0], tail[1]);
            parts.add((SnakePart) board.getElement(tail[0], tail[1]));
            setColor(color);
            pendingParts--;
        }
    }
    public void setColor(Color c){
        color = c;
        for (SnakePart snakePart: parts){
            snakePart.setColor(c);
        }
        head.setColor(new Color(0, 153, 51));
    }
    public Color getColor(){
        return color;
    }

    public int getDmg() {
        return dmg;
    }
}