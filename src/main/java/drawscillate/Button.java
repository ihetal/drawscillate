package drawscillate;

import processing.core.PApplet;
/*
 * Button class for creating custom buttons
 */
class Button extends LeafComponent {
    int x;
    int y;
    private PApplet applet;
    private String label;

    Button(PApplet applet, String label) {
        this.applet = applet;
        this.label = label;
    }
    /**
     * 
    * Function name - over
    * Description   - if mouse is over the button 
    * @param     - None
    * @return        - boolean
     */
    boolean over() {
        return applet.mouseX >= x
            && applet.mouseY >= y
            && applet.mouseX <= x + width()
            && applet.mouseY <= y + height();
    }
    /**
    * Function name - width
    * Description   - width according to the label
    * @param     - None
    * @return        - float
     */
    float width() {
        return applet.textWidth(label) + 8 * 2;
    }
    /**
    * Function name - height
    * Description   - Height of the button
    * @param     - None
    * @return        - float
     */
    private float height() {
        return 22 + 8 * 2;
    }
    public void display() {
        applet.stroke(0);
        applet.strokeWeight(1f);
        applet.fill(200);
        if (over()) {
            applet.fill(255);
        }
        applet.rect(x, y, width(), height());
        applet.fill(0);
        applet.text(label, x + 8, y + 8 + 20);
    }

}
