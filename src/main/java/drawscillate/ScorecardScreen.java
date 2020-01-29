package drawscillate;

import processing.core.PApplet;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;
/**
 * Score card screen
 */
public class ScorecardScreen implements IScreen {

    private PApplet applet;
    private int hits;
    private IScoringStrategy scoringStrategy;
    private Button buttonReplay;
    private Button buttonExit;
    private AppController appController;
    private String difficulty;
    private boolean gameWon;

    public ScorecardScreen(PApplet applet, String shape, String difficulty, int hits, boolean gameWon) {
        this.applet = applet;
        this.hits = hits;
        this.difficulty = difficulty;
        this.gameWon = gameWon;
        System.out.println(hits);

        appController = AppController.getInstance();

        switch (shape) {
            case "Heart":
                scoringStrategy = new HeartScoringStrategy();
                break;
            case "Circle":
                scoringStrategy = new CircleScoringStrategy();
                break;
            case "Rectangle":
                scoringStrategy = new RectangleScoringStrategy();
                break;
            case "Star":
                scoringStrategy = new StarScoringStrategy();
                break;
        }

        buttonReplay = new Button(applet, "Replay");
        buttonExit = new Button(applet, "Exit");
    }


    @Override
    public void mousePressed() {

    }
    /**
    * Function name - display
    * Description   - display the  score card screen
    * @param     None
    * @return        None-
     */
    @Override
    public void display() {
        applet.background(0);
        applet.fill(0, 153, 204);
        applet.textSize(33);
        applet.textAlign(CENTER);
        if (gameWon){
            applet.text("Congratulations "+appController.getName()+"! You Won!", applet.width / 2f, 100);
            applet.textSize(20);
            applet.text("Guess what? We've saved your \nwinning moment to boast about!", applet.width / 2f, 135);
        }
        else {
            applet.text("Better luck next time!", applet.width / 2f, 100);
            applet.textSize(20);
            applet.text("Don't lose hope", applet.width / 2f, 135);
        }


        applet.fill(237f, 97f, 21f);
        applet.textSize(30);
        applet.text("You have completed", applet.width / 2f, 230);
        applet.textSize(70);
        applet.text(Integer.toString(getScore(scoringStrategy))+"%", applet.width / 2f, 310);

        applet.textAlign(LEFT);
        applet.textSize(25);

        buttonReplay.x = (int) (applet.width/4f - buttonReplay.width()/2);
        buttonReplay.y = 380;
        buttonReplay.display();

        buttonExit.x = (int) (3*(applet.width/4f) - buttonExit.width()/2);
        buttonExit.y = 380;
        buttonExit.display();
    }
    /**
    * Function name - getScore
    * Description   - display completions percentage
    * @param     scoringStrategy
    * @return        - int
     */
    private int getScore(IScoringStrategy scoringStrategy){
        if(gameWon)
            return 100;
        return Math.min(scoringStrategy.calculateScore(hits*100,difficulty),98);
    }
    /**
    * Function name - willDisplay
    * Description   - 
    * @param     - 
    * @return        -
     */
    @Override
    public void willDisplay() {

    }

    @Override
    public void willStopDisplaying() {

    }

    @Override
    public void mouseReleased() {
        if (buttonReplay.over()) {
            System.out.println("REPLAY");
            AppController appController = AppController.getInstance();
            appController.update();
            return;
        }
        if (buttonExit.over()) {
            System.out.println("EXIT");
            applet.exit();
            return;
        }
    }

    @Override
    public void mouseDragged() {

    }
}
