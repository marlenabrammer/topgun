package games.topgun;

import java.awt.*;

class Plane {
    private boolean visible;
    private Image image;
    private boolean isDead;

    private int x_coordinate;
    private int y_coordinate;
    private int x_prime;

    public Plane() {
        setVisible(true);
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public int getX_prime() {
        return x_prime;
    }

    public void setX_prime(int x_prime) {
        this.x_prime = x_prime;
    }
}