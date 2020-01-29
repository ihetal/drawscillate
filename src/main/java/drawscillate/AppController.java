package drawscillate;

import processing.core.PApplet;

/**
 * Main class which is the starting point of the drawscillate application
 * 
 *
 */
public class AppController extends PApplet implements WelcomeScreenObserver, OptionsScreenObserver,IBackButtonObserver {
    private static AppController appController;
    private IScreen welcomeScreen;
    private IScreen optionsScreen;
    private IScreen gameScreen;
    private IScreen current;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
        AppController mySketch = AppController.getInstance();
        PApplet.runSketch(processingArgs, mySketch);
    }

    /**
     * Get Singleton Instance
     * @return Reference to Current App  (Create if none exists)
     */
    public synchronized static AppController getInstance() {
        if (appController == null) {
            appController = new AppController();
        }
        return appController;
    }
    /**
    * Function name - settings
    * Description   - setup the app
    * @param     - none
    * @return        - none
     */
    public void settings() {
        size(500, 500);
        smooth();
    }
    
    public void setup() {
        surface.setTitle("Drawscillate");
        final WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.attach(this);
        this.welcomeScreen = welcomeScreen;
        current = welcomeScreen;
    }

    private void setupOptionsScreen() {
         GameScreen gameScreen = new GameScreen(this);
        this.gameScreen = gameScreen;
        final OptionsScreen optionsScreen = new OptionsScreen(this);
        optionsScreen.attach(this);
        optionsScreen.attach(gameScreen);
        this.optionsScreen = optionsScreen;
    }

    public void draw() {
        current.display();
    }
    /**
    * Function name - mousePressed
    * Description   - pass on the mouses event
    * @param     - none
    * @return        - none
     */
    @Override
    public void mousePressed() {
        current.mousePressed();
    }
    /**
    * Function name - mouseReleased
    * Description   - when mouse is released
    * @param     - None
    * @return        - None
     */
    @Override
    public void mouseReleased() {
        current.mouseReleased();
    }
    
    /**
    * Function name - mouseDragged
    * Description   - mouse drag event
    * @param     - none
    * @return        - none
     */

    @Override
    public void mouseDragged() {
        current.mouseDragged();
    }
    /**
    * Function name - update
    * Description   - Update the screen to options screen
    * @param     - None
    * @return        -None
     */
    @Override
    public void update() {
        current.willStopDisplaying();
        setupOptionsScreen();
        current = optionsScreen;
        current.willDisplay();
    }
    /**
    * Function name - update2
    * Description   - display game screen
    * @param  difficultySelection 
    * @param shapeSelection
    * @return      None
     */
    @Override
    public void update2(String difficultySelection, String shapeSelection) {
        current.willStopDisplaying();
        current = gameScreen;
        current.willDisplay();
    }
    /**
    * Function name - update3
    * Description   - Display Score Card
    * @param   shapeSelection
    * @param  difficultySelection
    * @param hits
    * @param gameWon
    * @return        - void
     */
    public  void update3(String shapeSelection,String difficultySelection, int hits, boolean gameWon){
        current.willStopDisplaying();
        current = new ScorecardScreen(this,shapeSelection,difficultySelection,hits,gameWon);
        current.willDisplay();
    }

    /**
    * 
    * Function name - backButtonEvent
    * Description   - event of pressing back button
    * @param     - None 
    * @return        - None
    */
    @Override
    public void backButtonEvent() {
        
        update();
    }
}
