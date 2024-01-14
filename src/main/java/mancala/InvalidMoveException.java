package mancala;
import java.io.Serializable;

public class InvalidMoveException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
     /**
     * Throw Excepiton
     *
     */
    public InvalidMoveException() {
        super("Invalid Move!");
    }

     /**
     * Throw Exception with method
     *
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}