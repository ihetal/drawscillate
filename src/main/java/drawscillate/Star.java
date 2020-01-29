package drawscillate;

import static processing.core.PConstants.CLOSE;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Star extends Shapes implements IShapes {

    @Override
    public int[][] draw(int weight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(weight);
        graphics.beginShape();
        setCheckpointCount(10);
        int startX = 550 / 2 - 47 / 2;
        int startY = 160 / 2 - 45 / 2;
        graphics.vertex(startX, startY);
        insertCheckPoint(startX, startY, 0);
        graphics.vertex(startX + 60, startY + 140);
        insertCheckPoint(startX + 60, startY + 140, 1);
        graphics.vertex(startX + 230, startY + 150);
        insertCheckPoint(startX + 230, startY + 150, 2);
        graphics.vertex(startX + 100, startY + 240);
        insertCheckPoint(startX + 100, startY + 240, 3);
        graphics.vertex(startX + 180, startY + 380);
        insertCheckPoint(startX + 180, startY + 380, 4);
        graphics.vertex(startX, startY + 300);
        insertCheckPoint(startX, startY + 300, 5);
        graphics.vertex(startX - 180, startY + 380);
        insertCheckPoint(startX- 180, startY + 380, 6);
        graphics.vertex(startX - 100, startY + 240);
        insertCheckPoint(startX -100, startY + 240, 7);
        graphics.vertex(startX - 230, startY + 150);
        insertCheckPoint(startX -230, startY + 150, 8);
        graphics.vertex(startX - 60, startY + 140);
        insertCheckPoint(startX - 60, startY + 140, 9);
        //graphics.vertex(startX, startY);
        graphics.endShape(CLOSE);
        graphics.endDraw();
        applet.image(graphics, 0, 0);
        return getCheckpoints();
    }

    @Override
    public String getName() {
        return "Star";
    }

}
