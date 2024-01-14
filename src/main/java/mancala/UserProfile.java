package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private int kalahGames;
    private int kalahWins;
    private int ayoGames;
    private int ayoWins;

    /**
     * Initialize user profile
     */
    public UserProfile () {
        userName = "";
        kalahGames = 0;
        kalahWins = 0;
        ayoGames = 0;
        ayoWins = 0;
    }

    /**
     * Set name of profile
     */
    public void setName(String name) {
        userName = name;
    }

    /**
     * GetName
     *
     * @return user name
     */
    public String getName() {
        return userName;
    }

    /**
     * Add Kalah Game
     */
    public void addKalahGame() {
        kalahGames++;
    }
    
    /**
     * Add Kalah win
     */
    public void addKalahWin() {
        kalahWins++;
    }

    /**
     * Add Ayo game
     */
    public void addAyoGame() {
        ayoGames++;
    }

    /**
     * Add Ayo win
     */
    public void addAyoWin() {
        ayoWins++;
    }

    /**
     * Get Kalah Games
     */
    public int getKalahGames() {
        return kalahGames;
    }

    /**
     * Get Kalah wins
     */
    public int getKalahWins() {
        return kalahWins;
    }

    /**
     * Get Ayo Games
     */
    public int getAyoGames() {
        return ayoGames;
    }

    /**
     * Get Ayo wins
     */
    public int getAyoWins() {
        return ayoWins;
    }
}