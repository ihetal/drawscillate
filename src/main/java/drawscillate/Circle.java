package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
/**
 * Circle class part of the factory pattern
 */
public class Circle extends Shapes implements IShapes {

    /**
    * Function name - draw
    * Description   - 
    * @param     weight of the stroke
    * @param    graphics object of processing
    * @param    Applet
    * @return     the list of checkpoints
     */
    @Override
    public int[][] draw(int weight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(weight);
        graphics.beginShape();
        graphics.ellipse(250, 250, 400, 400);
        graphics.endShape();
        graphics.endDraw();

        setCheckpointCount(4);

        insertCheckPoint(50, 250, 0);
        insertCheckPoint(250, 50, 1);
        insertCheckPoint(450, 250, 2);
        insertCheckPoint(250, 450, 3);

        applet.image(graphics, 0, 0);
        return getCheckpoints();
    }
    /**
     * 
    * Function name - getName
    * Description   - returns the class name for the dropdown
    * @param     - None 
    * @return        -None
     */
    @Override
    public String getName() {
        return "Circle";
    }
}
