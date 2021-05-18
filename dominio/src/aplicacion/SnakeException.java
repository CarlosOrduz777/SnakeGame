package aplicacion;

public class SnakeException extends Exception{
    public static final String GAME_OVER = "Fin del Juego";
    public static final String NO_ES_DAT = "El nombre del archivo debe tener la extension .dat";
    public static final String YA_EXISTE = "Este archivo ya existe en el destino escogido";
    public static final String GUARDAR = "No se logró guardar el archivo";
    public static final String NO_ES_DAT_IN = "El archivo que se quiere abrir no tiene la extensión .dat";
    public static final String NO_EXISTE ="En el directorio no existe un archivo con este nombre";
    public static final String ABRIR = "No se logró abrir el archivo";
    public SnakeException(String message){
        super(message);
    }
}
