package aplicacion;

import java.awt.*;

/**
 * Clase que construlle un muro
 * @author Carlos Orduz
 * @author Felipe Giraldo
 * @version 1.0
 */
public class Wall implements Element, java.io.Serializable{
    private final String name = "w";
    private int x;
    private int y;
    private Color color;

    public Wall (int y, int x){
        this.y = y;
        this.x = x;
        setColor(new Color(166, 166, 166));
    }

    @Override
    public int[] getPosition() {
        return new int[]{y, x};
    }

    @Override
    public void setPos(int[] to) {
        x = to[1];
        y = to[0];
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {

    }

    /**
     * Al ser comido el muro genera un game over
     * @param snake Serpiente que comio el muro
     * @throws SnakeException fin del juego
     */
    @Override
    public void eaten(Snake snake) throws SnakeException {
        throw new SnakeException(SnakeException.GAME_OVER);
    }

    /**
     * Al ser chocado por una fireball se elimina la pared y le da 5 puntos a la serpiente que lanzo la bola de fuego.
     * @param snake Serpiente que lanzo la bola de fuego
     */
    @Override
    public void fireballCheck(Snake snake) {
        snake.getBoard().deleteElement(getPosition());
        snake.setPendingParts(5);
        snake.setScore(snake.getScore()+5);
    }

    @Override
    public String getName() {
        return name;
    }
}
