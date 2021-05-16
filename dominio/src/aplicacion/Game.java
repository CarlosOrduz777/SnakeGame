package aplicacion;

/**
 * Clase que construye el juego SnOOPe modificacion del juego snake.
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Game{
    Board board;

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
}