package mancala;
import java.io.Serializable;
import java.util.Scanner;
import java.io.IOException;  
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Saver{
    /**
     * Save object to filename
     */
    public void saveObject(final Serializable toSave, final String filename) throws IOException {
        try(ObjectOutputStream outObject = new ObjectOutputStream(new FileOutputStream(filename))) {
            outObject.writeObject(toSave);
            outObject.close();
        } catch (IOException e) {
            throw new IOException("Error saving game to filename!");
        }
    }

    /**
     * Retuns object saved to filename
     */
    public Serializable loadObject(final String filename) throws IOException{
        try(ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(filename))) {
            return (Serializable) inObject.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error opening game from filename!");
        }
    }
}