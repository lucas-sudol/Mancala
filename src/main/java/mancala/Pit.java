package mancala;
import java.io.Serializable;

public class Pit implements Serializable,Countable{
    private static final long serialVersionUID = 1L;
    //Constructor Pit()
    private int stone;

     /**
     * Initialize pit with 0
     *
     */
    public Pit() {
        stone = 0;
    }

     /**
     * Stone count
     *
     * @return int stone count of pit
     */
    @Override
    public int getStoneCount() {
        return stone;
    }

     /**
     * Add stone
     *
     */
    @Override
    public void addStone() {
        stone += 1;
    }

     /**
     * Add stones
     *
     */
    @Override
    public void addStones(final int numToAdd) {
        stone += numToAdd;
    }

     /**
     * Remove stones
     *
     * @return number of stones removed
     */
    @Override
    public int removeStones() {
        final int stoneAmount = stone;
        stone = 0;
        return (stoneAmount);
    }

     /**
     * String method
     *
     * @return Integer representation of stone amount
     */
    @Override
    public String toString() {
        return Integer.toString(getStoneCount());
    }
}