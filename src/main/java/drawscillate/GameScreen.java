package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.sound.SinOsc;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static processing.core.PConstants.HAND;
/**
 * The actual game playing screen
 */
public class GameScreen implements IScreen, OptionsScreenObserver, IGameLogicObserver,IBackButtonSubject {
    private PApplet applet;
    private GamePlayStateMachine gameManager;
    private String difficultySelection;
    private String shapeSelection;
    private List<SinOsc> sineWaves;
    private float[] sineFreq;
    private int numSines = 5;
    private float yoffset;
    private float frequency;
    private float detune;
    private int traceCursor;
    private PGraphics graphics;
    int redColor = 0;
    int greenColor = 0;
    int blueColor = 0;
    int[] pixelsFrame;
    float red;
    float green;
    float blue;
    boolean gameOver = false;
    boolean gameWon = false;
    int strokeWeight;
    int[][] checkpoints;
    private boolean firstTime = true;
    private ShapeFactory shapeFactory;
    private IShapes shapes;
    private AppController appController;
    CustomizeLine customizeLine;
    IColorCommand showRedColor;
    IColorCommand showYellowColor;
    IColorCommand showGreenColor;
    IColorCommand showBlueColor;
    IColorCommand showOrangeColor;
    IColorCommand showPurpleColor;
    IColorCommand showBlackColor;
    CompletionCheck completionCheck;
    Memento memento = null;
    private Button button;
    /**
     * @param applet
     */
    GameScreen(PApplet applet) {
        appController = AppController.getInstance();
        this.attachObserver(appController);
        this.applet = applet;
        graphics = applet.createGraphics(500, 500);
        shapeFactory = new ShapeFactory();
        gameManager = new GamePlayStateMachine(applet);
        gameManager.registerObserver((IGameLogicObserver) this);
        sineWaves = IntStream
            .range(0, numSines)
            .mapToObj(i -> {
                // Calculate the amplitude for each oscillator
                final double sineVolume = (1.0 / numSines) / (i + 1);
                // Create the oscillators
                final SinOsc sinOsc = new SinOsc(applet);
                // Set the amplitudes for all oscillators
                sinOsc.amp((float) sineVolume);
                return sinOsc;
            })
            .collect(Collectors.toList());
        sineFreq = new float[numSines]; // Initialize array for Frequencies

        customizeLine = new CustomizeLine();

        initializeCommands();
        setReceivers(showRedColor, "apple.png", 255, 0, 0);
        setReceivers(showYellowColor, "banana.png", 255, 255, 51);
        setReceivers(showGreenColor, "grapes.png", 0, 255, 0);
        setReceivers(showBlueColor, "water.png", 0, 0, 255);
        setReceivers(showOrangeColor, "orange.png", 255, 165, 0);
        setReceivers(showPurpleColor, "eggplant.png", 147, 112, 219);
        setReceivers(showBlackColor, null, 0, 0, 0);

        colorItem('r', showRedColor);
        colorItem('y', showYellowColor);
        colorItem('b', showBlueColor);
        colorItem('p', showPurpleColor);
        colorItem('g', showGreenColor);
        colorItem(' ', showBlackColor);
        colorItem('o', showOrangeColor);
        
        completionCheck = new CompletionCheck();
    }
    /**
    * Function name - willDisplay
    * Description   - play the sound
    * @param     - None
    * @return        -None
     */
    @Override
    public void willDisplay() {
        sineWaves.forEach(SinOsc::play);
    }
    /**
    * Function name - willStopDisplaying
    * Description   - stop playing sound
    * @param     None
    * @return    None
     */
    @Override
    public void willStopDisplaying() {
        sineWaves.forEach(SinOsc::stop);
    }
    /**
    * Function name - display
    * Description   - display the updated screen 
    * @param     - None
    * @return      -None
     */
    @Override
    public void display() {
        if (firstTime) {
            shapes = shapeFactory.getShape(ShapesNames.valueOf(shapeSelection));
            if (shapes != null) {
                strokeWeight = getStrokeWeight(difficultySelection);
                checkpoints = shapes.draw(strokeWeight, graphics, applet);
                gameManager.setCheckPoints(checkpoints);
                gameManager.setStrokeWeight(strokeWeight);
            }
            firstTime = false;
        }
        button = new Button(applet, "Return");
        if(shapeSelection.equals("Heart")) {
            button.y =450 ;  
        }else {
            button.y =10 ;
        }
        button.x = 10;
        button.display();

        // Map mouseY from 0 to 1
        yoffset = PApplet.map(applet.mouseY, 0, applet.height, 0, 1);
        // Map mouseY logarithmically to 150 - 1150 to create a base frequency range
        frequency = PApplet.pow(1000, yoffset) + 150;
        // Use mouseX mapped from -0.5 to 0.5 as a detune argument
        detune = PApplet.map(applet.mouseX, 0, applet.width, -0.5f, 0.5f);

        for (int i = 0; i < numSines; i++) {
            sineFreq[i] = frequency * (i + 1 * detune);
            // Set the frequencies for all oscillators
            sineWaves.get(i).freq(sineFreq[i]);
        }

        if (applet.mousePressed) {
            mousePressed();
        }

        if (applet.keyPressed) {
            customizeLine.setKey(applet.key);
            customizeLine.initialize();
        }
    }
    
    boolean mouseRelease = false ;
    boolean drawLine = true;
    private IBackButtonObserver backButtonObserver;
    /**
    * Function name - mousePressed
    * Description   - draw the lines
    * @param     - None
    * @return        -None
     */
    public void mousePressed() {
        applet.stroke(redColor, greenColor, blueColor);
        applet.strokeWeight(5);
        
        if (button.over()) {
            this.notifyObserver();
            return;
        }
        
        if (!gameOver) {
            applet.line(applet.mouseX, applet.mouseY, applet.pmouseX, applet.pmouseY);
            gameManager.mouseEvent(graphics);
            if(mouseRelease) {
                List<Integer> points= completionCheck.restoreFromMemento(memento);
                int checkX = -1;
                int checkY = -1;
                if(points.size() != 0) {
                    checkX = points.get(0);
                    checkY = points.get(1);
                }
                int distance = ((applet.mouseX - checkX) * (applet.mouseX - checkX)) +
                        ((applet.mouseY - checkY) * (applet.mouseY - checkY));
                if(distance < 10) {
                    mouseRelease = false;
                    return;
                }
            }
            if (gameWon) {
                playSound("win.wav");
                System.out.println("Game successfully completed");
                applet.save("outputimage/"+shapeSelection+"_"+System.currentTimeMillis()+".png");
                appController.update3(shapeSelection,difficultySelection,traceCursor,true);
            }
        } else {
            playSound("lose.wav");
            appController.update3(shapeSelection,difficultySelection,traceCursor,false);
        }

    }
    /**
    * Function name - playSound
    * Description   - play the captured sound
    * @param s the file location of the sound
    * @return        - void
     */
    private void playSound(String s) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/" + s)));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
    * Function name - update2
    * Description   - update the shape and difficulty
    * @param  difficultySelection   
    * @param   shapeSelection   - 
    * @return        -None
     */
    @Override
    public void update2(String difficultySelection, String shapeSelection) {
        this.difficultySelection = difficultySelection;
        this.shapeSelection = shapeSelection;
    }

    /**
    * Function name - getStrokeWeight
    * Description   - set the width of the line
    * @param difficultySelection    - 
    * @return        - the size of the line
     */
    private int getStrokeWeight(String difficultySelection) {
        switch (difficultySelection) {
        case "Hard":
            return 10;
        case "Normal":
            return 25;
        case "Easy":
            return 50;
        }
        return 10;
    }
    /**
    * Function name - changeCursorAndColor
    * Description   - change the color of the line
    * @param    res
    * @return        - void
     */
    private void changeCursorAndColor(String resourceName, int redColor, int greenColor, int blueColor) {
        final Optional<PImage> imageOptional = Optional.ofNullable(resourceName).map("/"::concat)
                .map(getClass()::getResource).map(URL::getFile).map(applet::loadImage);
        if (imageOptional.isPresent()) {
            applet.cursor(imageOptional.get());
        } else {
            applet.cursor(HAND);
        }
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
    }

    /**
     * @param key    Various keyboard keys to change color
     * 
     * @param icolor map keys to their corresponding menu
     */
    private void colorItem(char key, IColorCommand icolor) {
        customizeLine.setColorItem(key, icolor);
    }

    /**
     * Set Receivers for ColorCommand
     * 
     * @param m            set a receiver for command
     * 
     * @param resourceName name of image to be loaded
     * 
     * @param redColor     red pixels
     * 
     * @param redColor     green pixels
     * 
     * @param redColor     blue pixels
     */
    private void setReceivers(IColorCommand m, String resourceName, int redColor, int greenColor, int blueColor) {
        m.setReceiver(new IColorReceiver() {
            /** Command Action */
            public void doAction() {
                changeCursorAndColor(resourceName, redColor, greenColor, blueColor);
            }
        });
    }

    /**
     * create command to be mapped to options
     */
    private void initializeCommands() {
        showRedColor = new ColorCommand();
        showYellowColor = new ColorCommand();
        showBlueColor = new ColorCommand();
        showPurpleColor = new ColorCommand();
        showGreenColor = new ColorCommand();
        showBlackColor = new ColorCommand();
        showOrangeColor = new ColorCommand();
    }
    /**
    * Function name - gameState
    * Description   - store win or loss of game
    * @param   gameOver
    * @param gameWon
    * @return     void
     */
    @Override
    public void gameState(boolean gameOver, boolean gameWon) {
        this.gameOver = gameOver;
        this.gameWon = gameWon;
    }

    @Override
    public void mouseDragged(){
        traceCursor++;
    }
    
    @Override
    public void mouseReleased() {
        completionCheck.set(applet.mouseX, applet.mouseY);
        memento = completionCheck.saveToMemento();
        mouseRelease = true;
    }

    /**
    * 
    * Function name - attachObserver
    * Description   - save the back button observer
    * @param  backButtonObserver
    * @return       None
    */
    @Override
    public void attachObserver(IBackButtonObserver backButtonObserver) {
       this.backButtonObserver = backButtonObserver;
        
    }

    /**
    * 
    * Function name - notifyObserver
    * Description   - notify 
    * @param     None
    * @return      None 
    */
    @Override
    public void notifyObserver() {
        this.backButtonObserver.backButtonEvent();
        
    }
}