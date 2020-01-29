package drawscillate;

import processing.core.PApplet;
import processing.core.PImage;

import java.net.URL;
import java.util.*;

import static javax.swing.JOptionPane.showInputDialog;
import static processing.core.PConstants.*;
/**
 * welcome screen
 */
public class WelcomeScreen implements IScreen {
    private PApplet applet;
    private final Button button;
    private Collection<WelcomeScreenObserver> welcomeScreenObservers = new HashSet<>();
    private AppController app;

    WelcomeScreen(PApplet applet) {
        this.applet = applet;
        button = new Button(applet, "Click to start");
        app = AppController.getInstance();
    }

    /**
    * Function name - mouseReleased
    * Description   - 
    * @param     - 
    * @return        -
     */
    @Override
    public void mouseReleased() {
        if (button.over()) {
            notifyAllWelcomeScreenObservers();
            app.setName(showInputDialog("Please enter your Name"));
        }
    }

    @Override
    public void display() {
        applet.background(230, 222, 204);
        // draw the label
        applet.textAlign(CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(70);

        final Optional<PImage> imageOptional =
            Optional
                .of("welcome.png")
                .map("/"::concat)
                .map(getClass()::getResource)
                .map(URL::getFile)
                .map(applet::loadImage);
        if (imageOptional.isPresent()) {
            applet.image(imageOptional.get(), 0, 0, applet.width, applet.height);
        } else {
            applet.text("Drawscillate", applet.width / 2f, applet.height / 2f);
        }

        // draw the button
        applet.textAlign(LEFT);
        applet.textSize(25);
        button.x = (int) Math.ceil(applet.width / 2f - button.width() / 2);
        button.y = 270;
        button.display();
    }
    /**
    * Function name - attach
    * Description   - 
    * @param  welcomeScreenObserver
    * @return        - void
     */
    void attach(WelcomeScreenObserver welcomeScreenObserver) {
        welcomeScreenObservers.add(welcomeScreenObserver);
    }
    /**
    * Function name - notifyAllWelcomeScreenObservers
    * Description   - 
    * @param    None
    * @return        - void
     */
    private void notifyAllWelcomeScreenObservers() {
        welcomeScreenObservers.forEach(WelcomeScreenObserver::update);
    }
}