package mancala;
import java.io.Serializable;

public class Store implements Serializable, Countable{
    private static final long serialVersionUID = 1L;
    //constructor Store()
    private int count;
    private Player owner;


    /**
     * Initialize store with 0 and no owner
     */
    public Store() {
        count = 0;
        owner = null;
    }

    /**
     * Get Store owner
     *
     * @return player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set owner
     */
    public void setOwner(Player player) {
        owner = player;
    }

    /**
     * Get stone count
     *
     * @return count of stones
     */
    @Override
    public int getStoneCount() {
        return count;
    }

    /**
     * Add stone to store
     */
    @Override
    public void addStone() {
        count += 1;
    }

    /**
     * Add stone amount to store
     */
    @Override
    public void addStones(int numToAdd) {
        count += numToAdd;
    }

    /**
     * Remove stones
     *
     * @return number of stones removed
     */
    @Override
    public int removeStones() {
        final int stoneAmount = count;
        count = 0;

        return stoneAmount;
    }

    /**
     * toString
     *
     * @return string representation of stone amount
     */
    @Override
    public String toString() {
        return Integer.toString(getStoneCount());
    }
}