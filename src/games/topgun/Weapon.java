package games.topgun;

import javax.swing.*;

public class Weapon extends Plane {
    public Weapon() {
    }

    public Weapon(int x, int y) {
        initWeapon(x, y);
    }

    private void initWeapon(int x, int y) {
        String pilotWeapon = "img/bullet_pilot.png";
        ImageIcon weapon = new ImageIcon(pilotWeapon);
        setImage(weapon.getImage());

        int H_SPACE = 6;
        setX_coordinate(x + H_SPACE);

        int V_SPACE = 1;
        setY_coordinate(y - V_SPACE);
    }
}