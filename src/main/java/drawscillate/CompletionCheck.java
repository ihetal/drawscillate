package drawscillate;

import java.util.ArrayList;
import java.util.List;
/**
 * Completions check and add it to list 
 */
public class CompletionCheck {
    private int pointX;
    private int pointY;
    /**
    * Function name - set
    * Description   - save the mouse coordinates
    * @param  pointX
    * @param pointY  
    * @return        - void
     */
    public void set(int pointX, int pointY)  
    { 
        this.pointX = pointX;
        this.pointY = pointY;
    } 
    /**
    * Function name - saveToMemento
    * Description   - save to Memento pattern
    * @param     -  None
    * @return        - Memento
     */
    public Memento saveToMemento()  
    { 
        return new Memento(pointX, pointY); 
    } 
    /**
    * Function name - restoreFromMemento
    * Description   - restore from memento class
    * @param     memento 
    * @return        - List<Integer>
     */
    public List<Integer> restoreFromMemento(Memento memento)  
    { 
        List<Integer> list = new ArrayList<>();
        pointX = memento.getSavedPointX();
        pointY = memento.getSavedPointY();
        list.add(pointX);
        list.add(pointY);
        return list;
    } 
}
