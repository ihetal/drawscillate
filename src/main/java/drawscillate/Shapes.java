package drawscillate;


public class Shapes {

    private int[][] checkpoints;

    public int[][] getCheckpoints() {
        return checkpoints;
    }

    /**
     * Create the checkpoint array for the given figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @param i The index of the coordinate
     */
    void insertCheckPoint(int x, int y, int i) {
        checkpoints[i][0] = x;
        checkpoints[i][1] = y;
        checkpoints[i][2] = 0;
    }
    /**
    * Function name - setCheckpointCount
    * Description   - create checkpoint object
    * @param  x 
    * @return        - void
     */
    void setCheckpointCount(int x){
        checkpoints = new int[x][3];
    }
}
