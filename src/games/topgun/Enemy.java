package games.topgun;

import javax.swing.ImageIcon;

class Enemy extends Plane {
    private Bomb weapon;

    public Enemy(int x, int y) {
        initEnemy(x, y);
    }

    private void initEnemy(int x, int y) {
        setX_coordinate(x);
        setY_coordinate(y);

        weapon = new Bomb(x, y);

        String enemyPath = "img/enemy.png";
        ImageIcon enemy = new ImageIcon(enemyPath);
        setImage(enemy.getImage());
    }

    public void act(int direction) {
        setX_coordinate(getX_coordinate() + direction);
    }

    public Bomb getBomb() {
        return weapon;
    }

    public class Bomb extends Plane {
        private boolean destroyed;

        public Bomb(int x, int y) {
            initBomb(x, y);
        }

        private void initBomb(int x, int y) {
            setDestroyed(true);

            setX_coordinate(x);
            setY_coordinate(y);

            String weaponPath = "img/bullet.png";
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