package drawscillate;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.DropdownList;
import controlP5.Textarea;
import processing.core.PApplet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;
import static processing.core.PConstants.CORNER;
/**
 * Option screens
 */
public class OptionsScreen implements IScreen, CallbackListener, IDisplayComponent {
    private PApplet applet;
    private ControlP5 controlP5;
    private DropdownList d1, d2;
    private final Button button;
    private String difficultySelection;
    private String shapeSelection;
    private Collection<OptionsScreenObserver> optionsScreenObservers = new HashSet<>();
    private ArrayList<IDisplayComponent> components = new ArrayList<IDisplayComponent>();
    private AppController app;
    private Textarea myTextArea;
    private TextComponent textComponent1;
    private TextComponent textComponent2;
    private TextComponent textComponent3;
    private TextComponent textComponent4;
    private TextComponent textComponent5;

    private String header = "";
    private String label1 = "How to play";
    private String label2 = "Move your cursor through the outline of the picture,\n "
            + "without crossing over the border to win!";
    private String label3 = "CHOOSE SHAPE:";
    private String label4 = "CHOOSE LEVEL:";
    
    /**
     * 
     * @param applet
     */
    OptionsScreen(PApplet applet) {
        this.applet = applet;
        controlP5 = new ControlP5(applet);
        app = AppController.getInstance();
        header = "Hi ! Select your Preferences!";
        textComponent1 = new TextComponent(applet,applet.width/2, 30, applet.width-100,30,header,CENTER,CENTER);
        textComponent1.setFill(237f, 97f, 21f);
        textComponent1.setTextSize(20f);
        textComponent2 = new TextComponent(applet,applet.width/2, 65, applet.width-100,25,label1);
        textComponent2.setTextSize(20f);
        textComponent2.setFill(48, 145, 50);
        textComponent3 = new TextComponent(applet,applet.width/2, 100, applet.width-100,60,label2);
        textComponent3.setTextSize(13f);
        textComponent4 = new TextComponent(applet,applet.width/4, 170, applet.width/4,40,label3,RIGHT,CENTER);
        textComponent4.setTextSize(13f);
        textComponent4.setFill(255, 255, 255);
        textComponent5 = new TextComponent(applet,applet.width/4, 220, applet.width/4,40,label4,RIGHT,CENTER);
        textComponent5.setTextSize(13f);
        textComponent5.setFill(255, 255, 255);
        addSubComponent(textComponent1);
        addSubComponent(textComponent2);
        addSubComponent(textComponent3);
        addSubComponent(textComponent4);
        addSubComponent(textComponent5);
        // create a DropdownList
        d1 = controlP5
            .addDropdownList("Difficulty")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(applet.width-300, applet.height-300)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(40)
            .setItemHeight(40)
            .addItems(Arrays.asList("Easy", "Normal", "Hard"));
        
        ShapeFactory factory = new ShapeFactory();
        // create a second DropdownList
        d2 = controlP5
            .addDropdownList("Shape")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(applet.width-300, applet.height-350)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(40)
            .setItemHeight(40)
            .addItems(Arrays.asList(factory.getShape(ShapesNames.Star).getName(),
                    factory.getShape(ShapesNames.Rectangle).getName(),
                    factory.getShape(ShapesNames.Heart).getName(),
                    factory.getShape(ShapesNames.Circle).getName()));
        
        myTextArea = controlP5
                    .addTextarea("txt")
                    .setPosition(applet.width/10f+10, applet.height/2f)
                    .setSize(applet.width-350 ,applet.height-300)
                    .setVisible(false)
                    .setFont(applet.createFont("Georgia",14))
                    .setLineHeight(18)
                    .setColor(applet.color(237, 97, 21))
                    .setText("Key Controls:\n\n"
                            +" y - Yellow\n r - Red\n"
                            +" g - Green\n b - Blue\n"
                            +" p - Purple \n o - Orange\n"
                            +" space - Black\n"
                            );

        button = new Button(applet, "Play!");
    }
    /**
    * Function name - addSubComponent
    * Description   - add sub component
    * @param  component
    * @return       None
     */
    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        
    }
    /**
    * Function name - mouseReleased
    * Description   - mouse released
    * @param     - None
    * @return        - None
     */
    @Override
    public void mouseReleased() {
        if (difficultySelection != null
            && shapeSelection != null
            && button.over()) {
            notifyAllOptionsScreenObservers();
        }
    }
    /**
    * Function name - willDisplay
    * Description   - display the the top and bottom bar
    * @param     - None
    * @return        -None
     */
    @Override
    public void willDisplay() {
        d1.setBarVisible(true);
        d2.setBarVisible(true);
        myTextArea.setVisible(true);
    }
    /**
    * Function name - willStopDisplaying
    * Description   - stop displaying
    * @param     - None
    * @return        -None
     */
    @Override
    public void willStopDisplaying() {
        d1.setBarVisible(false);
        d2.setBarVisible(false);
        myTextArea.setVisible(false);
    }
    /**
    * Function name - display
    * Description   - display options screen
    * @param     - None
    * @return        - None
     */
    @Override
    public void display() {
        
        header = "Hi " + app.getName() + "! Select your Preferences!";
        textComponent1.setLabel(header);
        applet.background(0);
        applet.noFill();
        applet.stroke(0, 153, 204);
        applet.rectMode(CENTER);
        applet.rect(applet.height / 2f, applet.width / 2f, applet.width - 100, applet.height - 100);
        // Text
        for (IDisplayComponent c : components)
            c.display();

        applet.rectMode(CORNER);
        // draw the button
        applet.textAlign(LEFT);
        applet.textSize(25);
        button.x = (int) Math.ceil(applet.width / 2f - button.width() / 2);
        button.y = 400;
        button.display();
    }
    /**
    * Function name - attach
    * Description   - attach the observer
    * @param   optionsScreenObserver 
    * @return      - void
     */
    void attach(OptionsScreenObserver optionsScreenObserver) {
        optionsScreenObservers.add(optionsScreenObserver);
    }
    /**
    * Function name - notifyAllOptionsScreenObservers
    * Description   - notify observer
    * @param     - None
    * @return        - void
     */
    private void notifyAllOptionsScreenObservers() {
        optionsScreenObservers.forEach(optionsScreenObserver -> optionsScreenObserver.update2(difficultySelection, shapeSelection));
    }

    /**
    * Function name - controlEvent
    * Description   - display the shape options or the difficulty options
    * @param   callbackEvent
    * @return       void
     */
    @Override
    public void controlEvent(CallbackEvent callbackEvent) {
        final Controller controller = callbackEvent.getController();
        final int selectedIndex = (int) controller.getValue();
        if (controller == d1) {
            difficultySelection = controlP5.get(DropdownList.class, "Difficulty").getItem(selectedIndex).get("name").toString();
        } else if (controller == d2) {
            shapeSelection = controlP5.get(DropdownList.class, "Shape").getItem(selectedIndex).get("name").toString();
        }
    }


}


