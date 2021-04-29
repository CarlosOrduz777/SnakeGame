package aplicacion;

public interface Element {
    String name = " ";

    int[] getPosition();
    default String getName(){
        return name;
    }
    default void setPos(int[] to){}

}