package games.topgun;

import javax.swing.ImageIcon;

class Enemy extends Plane {
    private Missile weapon;
    private final int y_Velocity = 3;

    public Enemy(int x_coordinate, int y_coordinate) {
        initEnemy(x_coordinate, y_coordinate);
    }

    private void initEnemy(int x_coordinate, int y_coordinate) {
        setX_coordinate(x_coordinate);
        setY_coordinate(y_coordinate);

        weapon = new Missile(x_coordinate, y_coordinate);

        String enemyPath = "img/enemy.png";
        ImageIcon enemy = new ImageIcon(enemyPath);
        setImage(enemy.getImage());
    }

    public void move() {
        setY_coordinate(getY_coordinate() + y_Velocity);
       // repaint();
    }

    public Missile getWeapon() {
        return weapon;
    }

    public class Missile extends Plane {
        private boolean destroyed;

        public Missile(int x_coordinate, int y_coordinate) {
            initWeapon(x_coordinate, y_coordinate);
        }

        private void initWeapon(int x_coordinate, int y_coordinate) {
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
}