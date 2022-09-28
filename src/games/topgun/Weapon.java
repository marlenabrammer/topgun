package games.topgun;

import javax.swing.ImageIcon;

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

        int H_SPACE = 6;
        setX_coordinate(x_coordinate + H_SPACE);

        int V_SPACE = 1;
        setY_coordinate(y_coordinate - V_SPACE);
    }
}