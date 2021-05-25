package test;

import aplicacion.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;


public class SnakeTest {

    @Test
    public void shouldMoveUp() {
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        board.move('u');
        board.turnTest();
        assertArrayEquals(new int[]{4, 2},snake.getHeadPos());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }
    @Test
    public void shouldMoveDown() {
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        board.move('d');
        board.turnTest();
        assertArrayEquals(new int[]{6, 2},snake.getHeadPos());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }

    }
    @Test
    public void shouldMoveLeft()  {
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        board.move('u');
        board.turnTest();
        board.move('l');
        board.turnTest();
        assertArrayEquals(new int[]{4, 1},snake.getHeadPos());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }

    }
    @Test
    public void shouldMoveRight()  {
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        board.move('r');
        board.turnTest();
        assertArrayEquals(new int[]{5, 3},snake.getHeadPos());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }

    }

    @Test
    public void shouldDie1(){
        Board board = new Board(5,2);
        board.move('u');
        board.turnTest();
        board.move('l');
        board.turnTest();
        board.turnTest();
        board.turnTest();
        assertFalse(board.getStatus());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldDie2(){
        Board board = new Board(5,2);
        RainbowFruit fruit = new RainbowFruit(5,3);
        board.addElement(5,3,fruit);
        board.turnTest();
        board.move('u');
        board.turnTest();
        board.move('l');
        board.turnTest();
        board.move('d');
        board.turnTest();
        assertFalse(board.getStatus());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldEatFruit(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        Fruit fruit = new Fruit(5,3);
        board.addElement(5,3,fruit);
        board.turnTest();
        assertEquals(1,snake.getScore());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldEatRainbowFruit(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        RainbowFruit fruit = new RainbowFruit(5,3);
        board.addElement(5,3,fruit);
        board.turnTest();
        assertEquals(3,snake.getScore());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldEatCandy(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        RainbowFruit fruit = new RainbowFruit(5,3);
        Candy candy =new Candy(5,4);
        board.addElement(5,3,fruit);
        board.addElement(5,4,candy);
        board.turnTest();
        board.turnTest();
        assertEquals(2,snake.getScore());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldEatPoison(){
        Board board = new Board(5,2);
        Poison poison = new Poison(5,3);
        board.addElement(5,3,poison);
        board.turnTest();
        assertFalse(board.getStatus());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldIncreaseSize(){
        Board board = new Board(5,2);
        Fruit fruit = new Fruit(5,3);
        board.addElement(5,3,fruit);
        board.turnTest();
        board.turnTest();
        assertEquals("s",board.readBoard()[5][1]);
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldPickDivision(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        Division division = new Division(5,3);
        board.addElement(5,3,division);
        board.turnTest();
        assertEquals("Division",snake.getSurpriseName());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldUseDivision(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        Division division = new Division(5,3);
        board.addElement(5,3,division);
        RainbowFruit rf = new RainbowFruit(5,4);
        board.addElement(5,4,rf);
        Fruit f = new Fruit(5,5);
        board.addElement(5,5,f);
        board.turnTest();
        board.turnTest();
        board.turnTest();
        board.turnTest();
        snake.useSurprise();
        board.turnTest();
        board.turnTest();
        assertEquals(2,snake.getScore());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldPickFireStar(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        FireStar division = new FireStar(5,3);
        board.addElement(5,3,division);
        board.turnTest();
        assertEquals("Fire Star",snake.getSurpriseName());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldPickLupa(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        Lupa division = new Lupa(5,3);
        board.addElement(5,3,division);
        board.turnTest();
        assertEquals("Lupa",snake.getSurpriseName());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldUseLupa(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        Lupa lupa = new Lupa(5,3);
        board.addElement(5,3,lupa);
        RainbowFruit rf = new RainbowFruit(5,4);
        board.addElement(5,4,rf);
        board.turnTest();
        snake.useSurprise();
        board.turnTest();
        assertEquals(0,snake.getScore());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }

    @Test
    public void shouldPickTrapWall(){
        Board board = new Board(5,2);
        Snake snake = board.getSnake(1);
        TrapWall tp = new TrapWall(5,3);
        board.addElement(5,3,tp);
        board.turnTest();
        assertEquals("Trap Wall",snake.getSurpriseName());
        String[][] boardS = board.readBoard();
        for (String[] line:boardS){
            System.out.println(Arrays.toString(line));
        }
    }










}
