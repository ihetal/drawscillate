/**
 * 
 */
package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * @author rajee
 *
 */
public class LoseGameState implements IGameState {

    private IGamePlayStateMachine  machine;
    /**
     * @param gamePlayStateMachine
     */
    public LoseGameState(IGamePlayStateMachine machine) {
        this.machine = machine;
    }

    /**
    * 
    * Function name - handleMouseEvent
    * Description   - change to initial when lost
    * @param     - none 
    * @return        - none
    */
    @Override
    public void handleMouseEvent(PApplet applet, PGraphics graphics) {
        this.machine.setStateInitial();
        
    }

}
