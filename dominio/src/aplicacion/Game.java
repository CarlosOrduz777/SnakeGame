package aplicacion;

public class Game{

    Board board;
    public Game (int players, boolean bot) {
        board = new Board(players);
    }

    public Board getBoard(){
        return board;
    }
}