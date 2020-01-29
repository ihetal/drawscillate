package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
/**
 *  Rectangel shape
 */
public class Rectangle extends Shapes implements IShapes {

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
        graphics.rect(50, 100, 400, 280);
        graphics.endShape();
        graphics.endDraw();

        setCheckpointCount(4);

        insertCheckPoint(50, 100, 0);
        insertCheckPoint(450, 100, 1);
        insertCheckPoint(50, 380, 2);
        insertCheckPoint(450, 380, 3);
        applet.image(graphics, 0, 0);
        return getCheckpoints();
    }
    /**
    * Function name - getName
    * Description   - name
    * @param     - None
    * @return        - name
     */
    @Override
    public String getName() {
        return "Rectangle";
    }
}
