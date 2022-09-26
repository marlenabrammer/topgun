package games.topgun;

import javax.swing.*;

class Enemy extends Plane {
    private Bomb weapon;

    public Enemy(int x_coord, int y_coord) {
        initEnemy(x_coord, y_coord);
    }

    private void initEnemy(int x_coord, int y_coord) {
        setX_coordinate(x_coord);
        setY_coordinate(y_coord);

        weapon = new Bomb(x_coord, y_coord);

        String enemyPath = "img/enemy.png";
        ImageIcon enemy = new ImageIcon(enemyPath);
        setImage(enemy.getImage());
    }

    public void move(int direction) {
        int x = getX_coordinate() + direction;
    }

    public Bomb getWeapon() {
        return weapon;
    }

    public class Bomb extends Plane {
        private boolean destroyed;

        public Bomb(int x_coord, int y_coord) {
            initWeapon(x_coord, y_coord);
        }

        private void initWeapon(int x_coord, int y_coord) {
            setDestroyed(true);

            setX_coordinate(x_coord);
            setY_coordinate(y_coord);

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
}