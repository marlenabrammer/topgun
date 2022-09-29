package games.topgun;

import java.awt.Image;

class Plane {
    // Instance variables
    private boolean visible = true;
    private Image image;
    private boolean dead;

    private int x_coordinate;
    private int y_coordinate;
    private int x_prime;
    private int y_prime;

    // Constructor(s)
    public Plane() {
    }

    // Accessor(s)
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
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
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

    public int getY_prime() {
        return y_prime;
    }

    public void setY_prime(int y_prime) {
        this.y_prime = y_prime;
    }
}