package aplicacion;

public class SnakeException extends Exception{
    public static final String GAME_OVER = "Fin del Juego";
    public SnakeException(String message){
        super(message);
    }
}
