package games.topgun;

import javax.swing.ImageIcon;

public class Weapon extends Plane {
    public Weapon() {
    }

    public Weapon(int x, int y) {
        initShot(x, y);
    }

    private void initShot(int x, int y) {
         String weaponPath = "img/bullet.png";
         ImageIcon weapon = new ImageIcon(weaponPath);
         setImage(weapon.getImage());

        int H_SPACE = 6;
        setX_coordinate(x + H_SPACE);

        int V_SPACE = 1;
        setY_coordinate(y - V_SPACE);
    }
}