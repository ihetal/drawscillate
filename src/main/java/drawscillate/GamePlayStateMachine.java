/**
 * 
 */
package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
/**
 *  State machine of the  
 */
public class GamePlayStateMachine implements IGamePlayStateMachine,IGameLogicSubject {
    
    private InitialGameState initialGameState;
    private InplayGameState inplayGameState;
    private LoseGameState loseGameState;
    private WinGameState winGameState;
    
    private IGameState currentState;
    
    private IGameLogicObserver observer;
    private boolean gameOver;
    private boolean gameWon;
    private PApplet applet;
    int[][] checkpoints;
    int strokeWeight;
    int startPointX;
    int startPointY;
    
    
    /**
     * 
     * @param applet
     */
    public GamePlayStateMachine(PApplet applet) {
      initialGameState = new InitialGameState(this);
      inplayGameState = new InplayGameState(this);
      loseGameState = new LoseGameState(this);
      winGameState = new  WinGameState(this);
      currentState = initialGameState;
      this.applet = applet;
    }
    
    /**
    * Function name - setCheckPoints
    * Description    set the checkpoints 
    * @param  points 
    * @return        - void
     */
    public void setCheckPoints(int[][] points) {
        checkpoints = points;
    }
    /**
    * Function name - setStrokeWeight
    * Description   - weight ofthe line
    * @param   strokeWeight 
    * @return        - void
     */
    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
    }
    /**
    * Function name - setStartPoints
    * Description   - capture the start point
    * @param x    
    * @param y 
    * @return        - void
     */
    public void setStartPoints(int x, int y) {
        startPointX = applet.mouseX;
        startPointY = applet.mouseY;
    }
    /**
    * Function name - registerObserver
    * Description  register observer 
    * @param obj     - 
    * @return        - None
     */
    @Override
    public void registerObserver(IGameLogicObserver obj) {
        this.observer = obj;

    }
    /**
    * Function name - removeObserver
    * Description  remove observer 
    * @param     none
    * @return    None
     */
    @Override
    public void removeObserver() {
        this.observer = null;

    }
    
    /**
    * Function name - notifyObserver
    * Description   - notify observer
    * @param     - None
    * @return        -None
     */
    
    @Override
    public void notifyObserver() {
        observer.gameState(gameOver, gameWon);

    }
    /**
     * 
     * Function name - hasLineReachedCheckPoint Description - check if current point
     * is in the vicinity of some checkpoint
     * 
     * @param - mouseX
     * @param  -mouseY
     * @return - void
     */
    public void hasLineReachedCheckPoint() {

        for (int i = 0; i < checkpoints.length; i++) {
            if (checkpoints[i][2] != 1) {
                checkpoints[i][2] = isPointInCircle(checkpoints[i][0], checkpoints[i][1], applet.mouseX, applet.mouseY,
                        strokeWeight * strokeWeight);
            }
        }
    }

    /**
     *
     * Function name - isPointInCircle Description - check if point is within circle
     * with centre i ,j
     * 
     * @param i the x coordinate of the circle's center
     * @param j the y coordiante of the circles center
     * @param  mouseX   current pointer coordiante
     * @param mouseY    
     * @param radius    radius of the circle
     * @return - int
     */
    public int isPointInCircle(int i, int j, int mouseX, int mouseY, int radius) {
        int distance = (i - mouseX) * (i - mouseX) + (j - mouseY) * (j - mouseY);
        if (distance <= radius) {
            return 1;
        }
        return 0;
    }

    /**
     * @return If all check points have been reached
     */
    public boolean allCheckPointsReached() {
        if (checkpoints.length == 0)
            return false;
        for (int i = 0; i < checkpoints.length; i++) {
            if (checkpoints[i][2] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return If the start point been visited again
     */
    public boolean startReached() {
        return isPointInCircle(startPointX, startPointY, applet.mouseX, applet.mouseY, 100) == 1;
    }
    /**
    * 
    * Function name - setStateInitial
    * Description   - set to initial state
    * @param     - None
    * @return       NOne
    */
    @Override
    public void setStateInitial() {
        currentState = initialGameState;
        
    }

    /**
    * 
    * Function name - setStateInPlay
    * Description   - when the player is playing the game
    * @param     - None
    * @return        - None
    */
    @Override
    public void setStateInPlay() {
            currentState = inplayGameState;
    }

    /**
    * 
    * Function name - setStateWin
    * Description   - when the player wins
    * @param     - None
    * @return        - None
    */
    @Override
    public void setStateWin() {
            currentState = winGameState;
            gameWon = true;
            notifyObserver();
    }

    /**
    * 
    * Function name - setStateLose
    * Description   - when he loses
    * @param     - None
    * @return        - None
    */
    @Override
    public void setStateLose() {
            currentState = loseGameState;
            gameOver = true;
            notifyObserver();
    }
    
    /**
    * Function name - mouseEvent
    * Description   - handle mouse event
    * @param  graphics
    * @return        - void
     */
    public void mouseEvent(PGraphics graphics) {
        this.currentState.handleMouseEvent(applet,graphics);
    }
    

}
