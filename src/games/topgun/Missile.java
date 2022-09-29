package games.topgun;

import javax.swing.*;

class Missile extends Weapon {
    private boolean destroyed;

    public Missile(int x_coordinate, int y_coordinate) {
        initWeapon(x_coordinate, y_coordinate);
    }

    @Override
    public void initWeapon(int x_coordinate, int y_coordinate) {
        setDestroyed(true);

        setX_coordinate(x_coordinate);
        setY_coordinate(y_coordinate);

        String weaponPath = "img/bullet_enemy.png";
        ImageIcon weapon = new ImageIcon(weaponPath);
        setImage(weapon.getImage());
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}