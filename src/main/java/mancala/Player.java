package mancala;
import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    //Constructors Player() and Player(String name)
    private UserProfile playerProfile;
    private Store playerStore;

     /**
     * Initialize player name and user profile
     */
    public Player(final String name) {
        playerProfile = new UserProfile();
        playerProfile.setName(name);
        playerStore = null;

    }

     /**
     * Default empty name
     */
    public Player() {
        this("");
    }

     /**
     * Get name
     *
     * @return User profile name
     */
    public String getName() {
        return playerProfile.getName();
    }

     /**
     * Get store
     *
     * @return player store
     */
    public Store getStore() {
        return playerStore;
    }

     /**
     * Get store count
     *
     * @return the stone count of the player store
     */
    public int getStoreCount() {
        if (playerStore == null) {
            return 0;
        }
        return (playerStore.getStoneCount());
    }

     /**
     * Set name
     */
    public void setName(final String name) {
        playerProfile.setName(name);
    }

     /**
     * Set store
     */
    public void setStore(final Store store) {
        playerStore = store;
    }

     /**
     * Add kalah game to count
     */
    public void addKalahGame() {
        playerProfile.addKalahGame();
    }
    
    /**
     * Add kalah game win to count
     */
    public void addKalahWin() {
        playerProfile.addKalahWin();
    }

    /**
     * Add Ayo game to count
     */
    public void addAyoGame() {
        playerProfile.addAyoGame();
    }

    /**
     * Add Ayo game win to count
     */
    public void addAyoWin() {
        playerProfile.addAyoWin();
    }

    /**
     * Get kalah games
     *
     * @return number of games
     */
    public int getKalahGames() {
        return playerProfile.getKalahGames();
    }

    /**
     * Get kalah game wins
     *
     * @return number of games
     */
    public int getKalahWins() {
        return playerProfile.getKalahWins();
    }

    /**
     * Get Ayo games
     *
     * @return number of games
     */
    public int getAyoGames() {
        return playerProfile.getAyoGames();
    }

    /**
     * Get Ayo game wins
     *
     * @return number of games
     */
    public int getAyoWins() {
        return playerProfile.getAyoWins();
    }

    /**
     * Name and store count
     *
     * @return string
     */
    @Override
    public String toString() {
        return (getName() + ": " + getStoreCount());
    }
}