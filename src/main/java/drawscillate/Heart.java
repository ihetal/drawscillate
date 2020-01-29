package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
/**
 * Heart Shape
 */
public class Heart extends Shapes implements IShapes {
    
    @Override
    public int[][] draw(int strokeWeight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(strokeWeight);
        graphics.beginShape();
        
        final int x1 = applet.width / 2;
        final int halfHeartWidth = 500;
        final int y1 = 100;
        final int y2 = -50;
        final int y3 = 5;
        final int y4 = 485;
        graphics.vertex(x1, y1);
        graphics.bezierVertex(x1, y2, x1 + halfHeartWidth, y3, x1, y4);
        graphics.bezierVertex(x1 - halfHeartWidth, y3, x1, y2, x1, y1);
        graphics.endShape();
        graphics.endDraw();

        setCheckpointCount(5);
        insertCheckPoint(249, 93, 0);
        insertCheckPoint(29, 157, 1);
        insertCheckPoint(474, 158, 2);
        insertCheckPoint(252, 481, 3);
        insertCheckPoint(47, 219, 4);
        applet.image(graphics, 0, 0);
        return getCheckpoints();
    }
    /**
    * Function name - getName
    * Description   - Heart
    * @param     - None
    * @return        -None
     */
    @Override
    public String getName() {
        return "Heart";
    }
}
