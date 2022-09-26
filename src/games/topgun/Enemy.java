package games.topgun;

import javax.swing.*;

class Enemy extends Plane {
    private Weapon weapon;

    public Enemy(int x_coord, int y_coord) {
        initEnemy(x_coord, y_coord);
    }

    private void initEnemy(int x_coord, int y_coord) {
        setX_coordinate(x_coord);
        setY_coordinate(y_coord);

        weapon = new Weapon(x_coordinate, y_coordinate);

        String enemyPath = "img/";
        ImageIcon enemy = new ImageIcon(enemyPath);
        setImage(enemy.getImage());
    }

    public void move(int direction) {
        x_coordinate += direction;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public class Weapon extends Plane {
        private boolean destroyed;

        public Weapon(int x_coord, int y_coord) {
            initWeapon(x_coord, y_coord);
        }

        private void initWeapon(int x_coord, int y_coord) {
            setDestroyed(true);

            x_coordinate = x_coord;
            y_coordinate = y_coord;

            // String weaponPath = "img/";
            // ImageIcon weapon = new ImageIcon(weaponPath);
            // setImage(weapon.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}