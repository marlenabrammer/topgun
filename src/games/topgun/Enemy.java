package games.topgun;

import javax.swing.ImageIcon;

class Enemy extends Plane {
    private Missile missile;

    public Enemy(int x_coordinate, int y_coordinate) {
        String enemyPath = "img/enemy.png";
        ImageIcon enemy = new ImageIcon(enemyPath);
        setImage(enemy.getImage());

        setX_coordinate(x_coordinate);
        setY_coordinate(y_coordinate);

        missile = new Missile(x_coordinate, y_coordinate);
    }

    public void move() {
        setY_prime(1);
        setY_coordinate(getY_coordinate() + getY_prime());
    }

    public Missile getMissile() {
        return missile;
    }
}