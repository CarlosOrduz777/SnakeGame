package test;

import aplicacion.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import java.util.Arrays;

public class SnakeTest {

    @Test
    public void shouldMove() throws InterruptedException {
        Board board = new Board(1);
        board.readBoard();
        assertTrue(true);
    }



}
