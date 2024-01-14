package mancala;

public class KalahRules extends GameRules {
    private static final long serialVersionUID = 1L;
    private final MancalaDataStructure gameBoard;
    public KalahRules() {
        super();
        gameBoard = getDataStructure();
        gameBoard.setUpPits();
    }

     /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        if(startPit<1 || startPit > 12) { //out of bounds
            throw new InvalidMoveException("Pit number out of range!");
        } else if (startPit > 6 && playerNum == 1) { //pit number does not match player
            throw new InvalidMoveException("Pit number does not match Player!");
        } else if (startPit < 7 && playerNum == 2) { //pit number does not match player
            throw new InvalidMoveException("Pit number does not match Player!");
        } else {
            final int initialStones = gameBoard.getStoreCount(playerNum);

            setPlayer(playerNum);

            distributeStones(startPit);

            final int stoppingPoint = gameBoard.finalPosition();
            if(landEmptyPit(playerNum, stoppingPoint))  {
                gameBoard.addToStore(playerNum, captureStones(stoppingPoint+1));
            }

            return gameBoard.getStoreCount(playerNum) - initialStones;
        }

    }
    
     /**
     * Return stopping point
     *
     * @return The stopping point of iterator
     */
    @Override
    public int stoppingPoint() {
        return gameBoard.finalPosition();
    }

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    @Override
    /* deafault */ int distributeStones(final int startPit) {
        Countable currentPit;

        gameBoard.setIterator(startPit,getCurrentPlayer(),false);

        final int stoneNumber = gameBoard.removeStones(startPit);
        int stonesDistributed = 0;

        for(int i = 0; i < stoneNumber; i++) {
            currentPit = gameBoard.next();
            currentPit.addStone();
            stonesDistributed++;
        }
        return stonesDistributed;
    }

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    @Override
    /* default */ int captureStones(final int stoppingPoint) {
        return gameBoard.removeStones(13-stoppingPoint) + gameBoard.removeStones(stoppingPoint); // get stones directly opposite
    }
}