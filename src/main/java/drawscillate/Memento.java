package drawscillate;
/**
 * Memento pattern
 */
public class Memento {
    private int pointX;
    private int pointY;
    /**
     * @param pointX
     * @param pointY
     */
    public Memento(int pointX, int pointY)  
    { 
        this.pointX = pointX;
        this.pointY = pointY;
    } 
   
    /**
    * Function name - getSavedPointX
    * Description   - return x coordinate
    * @param     - None
    * @return        - int
     */
    public int getSavedPointX()  
    { 
        return pointX; 
    } 
    /**
    * Function name - getSavedPointY
    * Description   - return y coordinate
    * @param     - None
    * @return        - int
     */
    public int getSavedPointY()  
    { 
        return pointY; 
    } 
}
