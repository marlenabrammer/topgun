package games.topgun;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Pilot extends Plane {
    private int width;

    public Pilot() {
        initPilot();
    }

    private void initPilot() {
        String pilotPath = "img/pilot.png";
        ImageIcon pilot = new ImageIcon(pilotPath);
        width = pilot.getImage().getWidth(null);
        setImage(pilot.getImage());

        int START_X_COORDINATE = 500;
        setX_coordinate(START_X_COORDINATE);

        int START_Y_COORDINATE = 850;
        setY_coordinate(START_Y_COORDINATE);
    }

    public void move() {
         setX_coordinate(getX_coordinate() + getX_prime());
         if (getX_coordinate() <= 2) {
             setX_coordinate(2);
         }

         if (getX_coordinate() >= 950 - 2 * width) { //TODO:FIX MAGIC NUMBER
             setX_coordinate(950 - 2 * width);
         }

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
           setX_prime(-4);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setX_prime(4);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setX_prime(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setX_prime(0);
        }
    }
}