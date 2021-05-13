package aplicacion;

public class Game{

    Board board;
    public Game (int players, boolean bot) {
        if (players == 1){
            board = new Board(1);
        }
        else if (players == 2){
            //if you play with bot you can't play whit two players
            board = new Board(2);

        }
        //else throw exception only enter one or two players
    }
    public Game (int length,int width,int players, boolean bot){
        if (players == 1){
            board = new Board(length,width);
        }
        //else if (players == 2){
        //if you play with bot you can't play whit two players

        //}
        //else throw exception only enter one or two players
    }
    public Board getBoard(){
        return board;
    }



}