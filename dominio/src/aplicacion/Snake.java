package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private final Board board;
    private SnakePart head;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private final ArrayList<SnakePart> parts = new ArrayList<>();
    private Color color = Color.green;

    public Snake(int y,int x, Board board){
        this.board = board;
        if (x < 2){
            //throw the snake need at least two spaces to the left to start
            x = 2;
        }
        if (x == board.width){
            x = x -2;
        }
        for (int x1 = x; x1 >= (x-2); x1--){
            board.addSnakePart(y,x1);
            parts.add((SnakePart) board.getElement(y,x1));
        }
        head = parts.get(0);
        setRight(true);
        board.readBoard();
        move();
    }

    public int getScore(){
        return parts.size();
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

    public void move() {
        try {
            if (isDown()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0] + 1;
                to[1] = from[1];
                if (board.isFruit(to[0], to[1])) {
                    board.addSnakePart(to[0], to[1]);
                    parts.add(0, (SnakePart) board.getElement(to[0], to[1]));
                    head = parts.get(0);
                }
                else if (board.isSnake(to[0],to[1])){
                   throw new ArrayIndexOutOfBoundsException();
                }
                else {
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
            }
            if (isLeft()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] - 1;
                if (board.isFruit(to[0], to[1])) {
                    board.addSnakePart(to[0], to[1]);
                    parts.add(0, (SnakePart) board.getElement(to[0], to[1]));
                    head = parts.get(0);
                }
                else if (board.isSnake(to[0],to[1])){
                    throw new ArrayIndexOutOfBoundsException();
                }
                else {
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
            }
            if (isRight()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0];
                to[1] = from[1] + 1;
                if (board.isFruit(to[0], to[1])) {
                    board.addSnakePart(to[0], to[1]);
                    parts.add(0, (SnakePart) board.getElement(to[0], to[1]));
                    head = parts.get(0);
                }
                else if (board.isSnake(to[0],to[1])){
                    throw new ArrayIndexOutOfBoundsException();
                }
                else {
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
            }
            if (isUp()) {
                int[] from = head.getPosition();
                int[] to = new int[2];
                to[0] = from[0] - 1;
                to[1] = from[1];
                if (board.isFruit(to[0], to[1])) {
                    board.addSnakePart(to[0], to[1]);
                    parts.add(0, (SnakePart) board.getElement(to[0], to[1]));
                    head = parts.get(0);
                }
                else if (board.isSnake(to[0],to[1])){
                    throw new ArrayIndexOutOfBoundsException();
                }
                else {
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
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            board.setGame(false);
        }
    }

    public void setColor(Color c){
        color = c;
    }
    public Color getColor(){
        return color;
    }
}