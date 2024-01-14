package mancala;
import java.io.Serializable;
/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private static final long serialVersionUID = 1L;
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    /* default */ MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */ boolean isSideEmpty(final int pitNum){
        // This method can be implemented in the abstract class.
        boolean returnValue = true;
        if(pitNum > 0 && pitNum <= 12) {
            if(pitNum <= 12/2) {
                for(int i = 0; i < 12/2; i++) {
                    if(gameBoard.getNumStones(i+1) != 0) {
                        returnValue = false;
                    }
                }
            } else {
                for(int i = 12/2; i < 12; i++) {
                    if(gameBoard.getNumStones(i+1) != 0) {
                        returnValue = false;
                    }
                }
            }
        } else {
            returnValue = false;
        }
        return returnValue;

    }

    /* default */ boolean landEmptyPit(final int playerNum, final int stoppingPoint) {
        boolean returnValue;
        if(stoppingPoint == 13 ||  stoppingPoint == 6) {
            returnValue = false;
        }else if(playerNum == 1 && stoppingPoint < 7 && gameBoard.getNumStones(stoppingPoint+1) == 1) {
            returnValue = true;
        } else if (playerNum == 2 && stoppingPoint > 7 && gameBoard.getNumStones(stoppingPoint+1) == 1) { 
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

     /**
     * Return stopping point
     *
     * @return The stopping point of iterator
     */
    public abstract int stoppingPoint();

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */ abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */ abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
        final Store playerOneStore = new Store();
        final Store playerTwoStore = new Store();

        playerOneStore.setOwner(one);
        playerTwoStore.setOwner(two);
        one.setStore(playerOneStore);
        two.setStore(playerTwoStore);

        gameBoard.setStore(playerOneStore,1);
        gameBoard.setStore(playerTwoStore,2);

        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

     /**
     * Get the number of stones in a pit.
     *
     * @return Current player of game
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        return "Game is running";
    }
}
