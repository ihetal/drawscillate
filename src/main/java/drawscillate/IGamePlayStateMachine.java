package drawscillate;

public interface IGamePlayStateMachine {
    /**
    * Function name - setStateInitial
    * Description   - initial state
    * @param     - None
    * @return        - void
     */
    void setStateInitial();
    
    /**
    * Function name - setStateInPlay
    * Description   - In play state
    * @param     - None
    * @return        - void
     */
    void setStateInPlay();
    
    /**
    * Function name - setStateWin
    * Description   - Game won
    * @param     - None
    * @return        - void
     */
    void setStateWin();
    
    /**
    * Function name - setStateLose
    * Description   - Game lost
    * @param     - None
    * @return        - void
     */
    void setStateLose();
}
