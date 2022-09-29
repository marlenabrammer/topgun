package games.topgun;

import javax.swing.ImageIcon;

class Weapon extends Plane {
    public Weapon() {
    }

    public Weapon(int x_coordinate, int y_coordinate) {
        initWeapon(x_coordinate, y_coordinate);
    }

    public void initWeapon(int x_coordinate, int y_coordinate) {
        String pilotWeapon = "img/bullet_pilot.png";
        ImageIcon weapon = new ImageIcon(pilotWeapon);
        setImage(weapon.getImage());

        int horizontalPosition = 17;
        setX_coordinate(x_coordinate + horizontalPosition);

        int verticalPosition = 1;
        setY_coordinate(y_coordinate - verticalPosition);
    }
}