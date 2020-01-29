/**
 * 
 */
package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * state pattern
 *
 */
public class InitialGameState implements IGameState {

    private IGamePlayStateMachine machine;
    private int[] pixelsFrame;
    private float red;
    private float green;
    private float blue;
    
    public InitialGameState(IGamePlayStateMachine machine) {
        this.machine =machine;
       
    }
    
    /**
    * 
    * Function name - handleMouseEvent
    * Description   -  change to inplay state
    * @param     -      none
    * @return        - none
    */
    @Override
    public void handleMouseEvent(PApplet applet, PGraphics graphics) {
        pixelsFrame = graphics.get().pixels;
        red = applet.red(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        green = applet.green(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        blue = applet.blue(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        if (red != 255 && green != 255 && blue != 255) {
            this.machine.setStateLose();
        }
        else {
            ((GamePlayStateMachine)machine).setStartPoints(applet.mouseX,applet.mouseY); 
            this.machine.setStateInPlay();
        }
        
    }

}
