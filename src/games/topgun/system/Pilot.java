package games.topgun.system;

import javax.swing.*;
import java.awt.event.KeyEvent;

class Pilot extends Plane {
    private int width;
    private int START_X_COORDINATE = 270;
    private int START_Y_COORDINATE = 280;

    public Pilot() {
        initPilot();
    }

    private void initPilot() {
        String pilotPath = "/img/pilot.png";
        ImageIcon pilot = new ImageIcon(pilotPath);

        width = pilot.getImage().getWidth(null);
        setImage(pilot.getImage());

        setX_coordinate(START_X_COORDINATE);
        setY_coordinate(START_Y_COORDINATE);
    }

    public void act() {
         x_coordinate += x_prime;
         if (x_coordinate <= 2) {
             x_coordinate = 2;
         }

         if (x_coordinate >= 358 - 2 * width) {
             x_coordinate = 358 - 2 * width;
         }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            x_prime = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            x_prime = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            x_prime = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            x_prime = 0;
        }
    }
}