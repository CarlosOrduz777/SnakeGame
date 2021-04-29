package aplicacion;

public class Game{
    Pj[] players;
    Board board;
    public Game (int players, boolean bot) {
        if (players == 1){
            this.players = new Pj[1];
            this.players[0] = new Pj();
            board = new Board();
        }
        //else if (players == 2){
            //if you play with bot you can't play whit two players

        //}
        //else throw exception only enter one or two players
    }
    public Game (int length,int width,int players, boolean bot){
        if (players == 1){
            this.players = new Pj[1];
            this.players[0] = new Pj();
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