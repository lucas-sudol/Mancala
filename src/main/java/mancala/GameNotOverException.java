package mancala;
import java.io.Serializable;

public class GameNotOverException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    //Constructor GameNotOverException()

     /**
     * Throw exception
     *
     */
    public GameNotOverException() {
        super("Game not Over!");
    }
}