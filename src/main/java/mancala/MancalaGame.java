package mancala;
import java.util.ArrayList;
import java.io.Serializable;

public class MancalaGame implements Serializable{
    private static final long serialVersionUID = 1L;
    private GameRules gameBoard = null;
    private Player currentPlayer;
    private int playerOnePits = 6;

    private final ArrayList<Player> players;

     /**
     * Initialize players list
     *
     */
    public MancalaGame() {
        players= new ArrayList<>();
    }

     /**
     * Get gameBoard
     *
     * @return gameBoard 
     */
    public GameRules getBoard() {
        return gameBoard;
    }

     /**
     * Get current player
     *
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

     /**
     * Get number of stones at pit
     *
     * @return number of stones
     */
    public int getNumStones(final int pitNum) throws PitNotFoundException{
        if(pitNum<1 || pitNum > 12) {
            throw new PitNotFoundException("Incorrect Pit Number");
        } else {
            return gameBoard.getNumStones(pitNum);
        }
    }

     /**
     * Get store count
     *
     * @return number of stones of player
     */
    public int getStoreCount(final Player player) throws NoSuchPlayerException{
        if(players.contains(player)) {
            return player.getStoreCount();
        } else {
            throw new NoSuchPlayerException("Player does not exist within game!");
        }
    }

     /**
     * Get winner of game
     *
     * @return the player that won
     */
    public Player getWinner() throws GameNotOverException{ 
        int playerOneTotal = 0;
        int playerTwoTotal = 0;
        if(isGameOver()) {
            playerOneTotal += players.get(0).getStoreCount();
            playerTwoTotal += players.get(1).getStoreCount();

            for(int i = 0; i < 12; i++) {
                if(i <playerOnePits) {
                    playerOneTotal += gameBoard.getNumStones(i+1);
                } else {
                    playerTwoTotal += gameBoard.getNumStones(i+1);
                }
            }

            if(playerOneTotal > playerTwoTotal) {
                return players.get(0);
            } else if (playerOneTotal < playerTwoTotal) {
                return players.get(1);
            } else {
                return null;
            }

        } else {
            throw new GameNotOverException();
        }
    }

     /**
     * Check if game is over
     *
     * @return true or false
     */
    public boolean isGameOver() {
        return (gameBoard.isSideEmpty(1) || gameBoard.isSideEmpty(12));
    }

     /**
     * Make game move
     *
     * @return the amount added to player store
     */
    public int move(final int startPit) throws InvalidMoveException{ 
        try {
            int returnValue = gameBoard.moveStones(startPit, players.indexOf(currentPlayer) + 1);
            int stoppingPoint = gameBoard.stoppingPoint();

            if(stoppingPoint >= 0 && stoppingPoint < 7) {
                setCurrentPlayer(players.get(0));
            } else {
                setCurrentPlayer(players.get(1));
            }
            return returnValue;
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException("Incorrect Start Pit!");
        }
    }
    
     /**
     * Set board
     *
     */
    public void setBoard(GameRules theBoard) {
        gameBoard = theBoard;
    }

     /**
     * Set current player
     *
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
        gameBoard.setPlayer(players.indexOf(player)+1);
    }

     /**
     * Set players for game
     *
     */
    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players.add(onePlayer);
        players.add(twoPlayer);
        gameBoard.registerPlayers(onePlayer,twoPlayer);
    }

     /**
     * Start new game
     *
     */
    public void startNewGame() {
        gameBoard.resetBoard();
    }

    /**
     * Get player 1
     *
     * @return Player 1 with profile
     */
    public Player getPlayer1() {
        return players.get(0);
    }

    /**
     * Get player 2
     *
     * @return Player 2 with profile
     */
    public Player getPlayer2() {
        return players.get(1);
    }
    
     /**
     * Print text representation of game
     *
     */
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append(" \tPits\t 12\t 11\t 10\t 9\t 8\t 7\t\n");
        returnString.append(players.get(1).getName() + ": ");
        returnString.append(players.get(1).getStoreCount() + "\t\0\t ");
        for(int i = 12; i>6; i--) {
            returnString.append(gameBoard.getNumStones(i) + "\t");
            if(i != 7) {
                returnString.append(" ");
            }
        }
        returnString.append("\n\t\0\t ");

        for(int i = 1; i<7; i++) {
            returnString.append(gameBoard.getNumStones(i) + "\t ");
        }
        returnString.append(players.get(0).getName()  + ": ");
        returnString.append(players.get(0).getStoreCount() + "\n");
        returnString.append(" \tPits\t 1\t 2\t 3\t 4\t 5\t 6\t\n");

        return returnString.toString(); //Print current board
    }
}