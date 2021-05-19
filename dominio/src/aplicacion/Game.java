package aplicacion;

import java.io.*;
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase que construye el juego SnOOPe modificacion del juego snake.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Game implements java.io.Serializable{
    private Board board;
    private String[] names = new String[2];


    /**
     * Construye un tablero
     * @param players numero de jugadores
     * @param bot si se desea o no un bot
     */
    public Game (int players, boolean bot) {
        board = new Board(players);
    }

    /**
     * Retorna el objeto tablero almacenado en el jeugo
     * @return tablero almacenado en el juego
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Guarda el estado actual del juego
     * @param file juego que se desea guardar
     */
    public void save(File file) throws SnakeException {
        try {
            if (!file.getName().contains(".dat")){
                throw new SnakeException(SnakeException.NO_ES_DAT);
            }
            if(file.exists()){
                throw new SnakeException(SnakeException.YA_EXISTE);
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(this);
            out.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage()+"     "+ Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Abre un juego de SnakeGame
     * @param file el archivo que se desea abrir
     */
    public Game open(File file) throws ClassNotFoundException, SnakeException {
        try {
            if (!file.getName().contains(".dat")){
                throw new SnakeException(SnakeException.NO_ES_DAT_IN);
            }
            if (!file.exists()){
                throw new SnakeException(SnakeException.NO_EXISTE);
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Game game = (Game) in.readObject();
            in.close();
            return game;
        }
        catch (IOException e){
            throw new SnakeException(SnakeException.ABRIR);
        }
    }
    public void pause(){
        board.pause();
    }
    public void resume(){
        board.resume();
        runnable();
    }
   public void setNames(String[] names){
        this.names = names;
   }

    public String[] getNames() {
        return names;
    }


    /**
     * Actualiza el estado de la serpiente constantemente teniendo en cuenta el refresco de la GUI
     */
    public void runnable(){
        board.runnable();
    }

    public static void main(String ...args) {
        Game game = new Game(1,false);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!game.getBoard().getStatus()){
                    System.exit(0);
                }
                String[][] board = game.getBoard().readBoard();
                for (String[] line: board) {
                    System.out.println(Arrays.toString(line));
                }
            }
        };
        timer.schedule(task,0,1000);
    }
}