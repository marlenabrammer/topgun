 package games.topgun;

import javax.swing.ImageIcon;

import javax.swing.*;

public class Weapon extends Plane {
    public Weapon() {
    }

    public Weapon(int x_coordinate, int y_coordinate) {
        initWeapon(x_coordinate, y_coordinate);
    }

    private void initWeapon(int x_coordinate, int y_coordinate) {
        String pilotWeapon = "img/bullet_pilot.png";
        ImageIcon weapon = new ImageIcon(pilotWeapon);
        setImage(weapon.getImage());


        var shotImg = "src/images/shot.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX_coordinate(x_coordinate + H_SPACE);

        int V_SPACE = 20;
        setY_coordinate(y_coordinate - V_SPACE);
    }


}