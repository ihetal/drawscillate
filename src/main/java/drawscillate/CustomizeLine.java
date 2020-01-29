package drawscillate;
/**
 * Command pattern for changing color of the lines
 */
public class CustomizeLine {

    private char key;

    private IColorInvoker redCol;
    private IColorInvoker yellowCol;
    private IColorInvoker orangeCol;
    private IColorInvoker blueCol;
    private IColorInvoker purpleCol;
    private IColorInvoker greenCol;
    private IColorInvoker blackCol;

    public CustomizeLine() {

        redCol = new ColorOption();
        yellowCol = new ColorOption();
        orangeCol = new ColorOption();
        blueCol = new ColorOption();
        purpleCol = new ColorOption();
        greenCol = new ColorOption();
        blackCol = new ColorOption();

    }

    /**
     * set the value of key pressed
     * @param key the keyboard key pressed
     */
    public void setKey(char key) {
        this.key = key;
    }

    /**
     * set the value of key pressed
     * @param key the keyboard key pressed
     */
    public char getKey() {
        return key;
    }

    /**
     * invoke a function based on keyboard character
     */
    public void initialize() {
        switch (key) {
        case 'r':
            redCol.invoke();
            break;
        case 'y':
            yellowCol.invoke();
            break;
        case 'b':
            blueCol.invoke();
            break;
        case 'g':
            greenCol.invoke();
            break;
        case 'p':
            purpleCol.invoke();
            break;
        case 'o':
            orangeCol.invoke();
            break;
        case ' ':
            blackCol.invoke();
            break;
        }
    }

    /**
     * map options to command
     * @param key pressed on keyboard
     * @param color set color command to key
     */
    public void setColorItem(char key, IColorCommand color) {

        switch (key) {
        case ('r'):
            redCol.setCommand(color);
            break;
        case ('y'):
            yellowCol.setCommand(color);
            break;
        case ('b'):
            blueCol.setCommand(color);
            break;
        case ('g'):
            greenCol.setCommand(color);
            break;
        case ('p'):
            purpleCol.setCommand(color);
            break;
        case ('o'):
            orangeCol.setCommand(color);
            break;
        case (' '):
            blackCol.setCommand(color);
            break;

        }
    }
}
