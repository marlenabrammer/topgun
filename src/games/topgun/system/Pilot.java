package games.topgun.system;

import javax.swing.*;
import java.awt.*;

class Pilot extends Plane {
    private int width;
    private int START_X_COORDINATE = 270;
    private int START_Y_COORDINATE = 280;

    public Pilot() {
        initPlayer();
    }

    private void initPlayer() {
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
    }
}