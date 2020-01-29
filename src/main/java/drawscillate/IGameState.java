package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public interface IGameState {
    /** 
    * Function name - handleMouseEvent
    * Description   -  handle the mouse actions
    * @param  applet
    * @param    graphics 
    * @return        - void
     */
    void handleMouseEvent(PApplet applet, PGraphics graphics);
}
