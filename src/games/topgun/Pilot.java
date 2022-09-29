package games.topgun;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Pilot extends Plane {
    private int width;
    private int height;

    public Pilot() {
        String pilotPath = "img/pilot.png";
        ImageIcon pilot = new ImageIcon(pilotPath);
        setImage(pilot.getImage());
        width = pilot.getImage().getWidth(null);
        height = pilot.getImage().getHeight(null);

        int START_X_COORDINATE = 500;
        setX_coordinate(START_X_COORDINATE);

        int START_Y_COORDINATE = 850;
        setY_coordinate(START_Y_COORDINATE);
    }

    public void move() {
         setX_coordinate(getX_coordinate() + getX_prime());
         setY_coordinate(getY_coordinate() + getY_prime());

         if (getY_coordinate() <= 2) {
             setY_coordinate(2);
         }

         if (getX_coordinate() <= 2) {
             setX_coordinate(2);
         }

         if (getX_coordinate() >= 1000 - 1 * width) {
             setX_coordinate(1000 - 1 * width);
         }

        if (getY_coordinate() >= 950 - 2 * height) {
            setY_coordinate(950 - 2 * height);
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            setY_prime(-4);
        }

        if (key == KeyEvent.VK_LEFT) {
           setX_prime(-8);
        }

        if (key == KeyEvent.VK_DOWN) {
            setY_prime(4);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setX_prime(8);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            setY_prime(0);
        }

        if (key == KeyEvent.VK_LEFT) {
            setX_prime(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setX_prime(0);
        }

        if (key == KeyEvent.VK_DOWN) {
            setY_prime(0);
        }
    }
}