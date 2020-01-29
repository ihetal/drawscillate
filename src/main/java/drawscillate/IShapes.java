package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;


public interface IShapes {
    /**
    * Function name - draw
    * Description   - draw the shape
    * @param  weight
    * @param graphics
    * @param applet    
    * @return        - int[][]
     */
    public int[][] draw(int weight, PGraphics graphics, PApplet applet);

    public String getName();

}
