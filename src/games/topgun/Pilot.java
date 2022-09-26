package games.topgun;

import games.topgun.Commons;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

class Pilot extends Plane {
    private int width;

    public Pilot() {
        initPilot();
    }

    private void initPilot() {
        String pilotPath = "img/pilot.png";
        ImageIcon pilot = new ImageIcon(pilotPath);
        width = pilot.getImage().getWidth(null);
        setImage(pilot.getImage());

        int START_X_COORDINATE = 270;
        setX_coordinate(START_X_COORDINATE);

        int START_Y_COORDINATE = 270;
        setY_coordinate(START_Y_COORDINATE);
    }

    public void act() {
         setX_coordinate(getX_coordinate() + getX_prime());
         if (getX_coordinate() <= 2) {
             setX_coordinate(2);
         }

         if (getX_coordinate() >= Commons.BOARD_WIDTH - 2 * width) {
             setX_coordinate(Commons.BOARD_WIDTH - 2 * width);
         }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setX_prime(-2);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setX_prime(2);
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