/**
 * 
 */
package drawscillate;

/**
 * observer pattern for back button  
 */
public interface IBackButtonSubject {
    
    
    /**
    * Function name - attachObserver
    * Description   attach the observer
    * @param     backButtonObserver
    * @return        - void
     */
    public void attachObserver(IBackButtonObserver backButtonObserver);
    /**
    * Function name - notifyObserver
    * Description   - notify observer
    * @param     -  None
    * @return        - void
     */
    public void notifyObserver();
    

}
