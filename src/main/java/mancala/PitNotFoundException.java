package mancala;
import java.io.Serializable;

public class PitNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

     /**
     * Pit not found exception
     */
    public PitNotFoundException() {
        super("Pit not Found!");
    }  

     /**
     * Pit not found exception with message
     */

    public PitNotFoundException(final String message) {
        super(message);
    }
    
}