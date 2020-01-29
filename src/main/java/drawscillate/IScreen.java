package drawscillate;

public interface IScreen {
    /**
     * Send touch events to screen
     */
    default void mousePressed() {}

    /**
     * Displays screen components
     */
    void display() ;
    /**
    * Function name - willDisplay
    * Description   - display screen
    * @param     - None
    * @return        - void
     */
    default void willDisplay() {}
    /**
    * Function name - willStopDisplaying
    * Description   - stop displaying the screen
    * @param     - None
    * @return        - void
     */
    default void willStopDisplaying() {}
    /**
    * Function name - mouseReleased
    * Description   - mouse released
    * @param     - None
    * @return        - void
     */
    default void mouseReleased() {}
    /**
    * Function name - mouseDragged
    * Description   - dragged the mouse
    * @param     - None
    * @return        - void
     */
    default void mouseDragged() {}
}
