package mancala;
import java.io.Serializable;

public class NoSuchPlayerException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

     /**
     * No player exception
     *
     */
    public NoSuchPlayerException() {
        super("No such Player!");
    }


    /**
     * No such player with message
     *
     */
    public NoSuchPlayerException(String message) {
        super(message);
    }
}