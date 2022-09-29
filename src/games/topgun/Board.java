package games.topgun;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    // Static variables and constants
    private final static int PANEL_WIDTH = 1000;
    private final static int PANEL_HEIGHT = 1000;
    private final static int INITIAL_NUM_ENEMIES = 6;
    private final int OFFSET = 50;
    private final Image backgroundImage = new ImageIcon("img/terrain.jpg").getImage();
    private final Image explosion = new ImageIcon("img/bang.png").getImage();
    private final List<Enemy> ENEMIES = new ArrayList<>();

    // Instance variables
    private Pilot pilot;
    private Weapon weapon;
    private int deadEnemies = 0;
    private boolean gameRunning = true;
    private Timer timer;
    private int points = 0;
    private int life = 3;

    // Constructor(s)
    public Board() {
        addKeyListener(keyAdapter);
        setFocusable(true);
        timer = new Timer(15, new Board.GameCycle());
        initGame();
        timer.start();
    }

    // Helper methods used for initializing and drawing
    private void initGame() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        pilot = new Pilot();
        weapon = new Weapon();
        //create enemies
        for(int i = 0; i < INITIAL_NUM_ENEMIES; i++){
            ENEMIES.add(new Enemy((PANEL_WIDTH / INITIAL_NUM_ENEMIES) * i + OFFSET, 0));
        }
    }

    private void draw(Graphics g) {
        if (gameRunning) {
            drawBackground(g);
            drawEnemies(g);
            drawPilot(g);
            drawPilotWeapon(g);
            drawEnemyWeapon(g);
            drawScore(g);
        }
        else {
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
        }
    }

    private void drawBackground(Graphics g){
        g.drawImage(backgroundImage,0,0,this);
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy : ENEMIES) {
            if (enemy.isVisible()) {
                g.drawImage(enemy.getImage(), enemy.getX_coordinate(), enemy.getY_coordinate(), this);
            }
            if (enemy.isDead()) {
                enemy.setVisible(false);
            }
        }
    }

    private void drawPilot(Graphics g) {
        if (pilot.isVisible()) {
            g.drawImage(pilot.getImage(), pilot.getX_coordinate(), pilot.getY_coordinate(), this);
        }
        if (pilot.isDead()) {
            pilot.setVisible(false);
            gameRunning = false;
        }
    }

    private void drawPilotWeapon(Graphics g) {
        if (weapon.isVisible()) {
            g.drawImage(weapon.getImage(), weapon.getX_coordinate(), weapon.getY_coordinate(), this);
        }
    }

    private void drawEnemyWeapon(Graphics g) {
        for (Enemy enemy : ENEMIES) {
            Missile missile = enemy.getMissile();
            if (!missile.isDestroyed()) {
                g.drawImage(missile.getImage(), missile.getX_coordinate(), missile.getY_coordinate(), null);
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setFont(new Font("Arial",Font.BOLD,18));
        g.drawString("Score: " + this.points, 10, 890);
        g.drawString("Life:   "  + this.life, 10, 905);
    }

    private void gameOver(Graphics g) {
        String gameOver = "img/gameover.png";
        String winner = "img/winner.png";
        if(!pilot.isDead() && !gameRunning) {
            ImageIcon winnerImage = new ImageIcon(winner);
            g.drawImage(winnerImage.getImage(), 0, 0, this);
            drawScore(g);
        }
        else if (pilot.isDead()) {
            ImageIcon gameOverImage = new ImageIcon(gameOver);
            g.drawImage(gameOverImage.getImage(), 0, 0, this);
            drawScore(g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void update() {
        if (deadEnemies == INITIAL_NUM_ENEMIES) {
            gameRunning = false;
            timer.stop();
        }

        pilot.move();

        int pilotX_coordinate = pilot.getX_coordinate();
        int pilotY_coordinate = pilot.getY_coordinate();

        if (weapon.isVisible()) {

            int weaponX_coordinate = weapon.getX_coordinate();
            int weaponY_coordinate = weapon.getY_coordinate();

            for (Enemy enemy : ENEMIES) {

                int enemyX_coordinate = enemy.getX_coordinate();
                int enemyY_coordinate = enemy.getY_coordinate();

                if (enemy.isVisible() && weapon.isVisible()) {
                    if (weaponX_coordinate >= (enemyX_coordinate)
                            && weaponX_coordinate <= (enemyX_coordinate + enemy.getImage().getWidth(null))
                            && weaponY_coordinate >= (enemyY_coordinate)
                            && weaponY_coordinate <= (enemyY_coordinate + enemy.getImage().getHeight(null))) {

                        enemy.setImage(explosion);
                        enemy.setDead(true);
                        points += 100;
                        deadEnemies++;
                        weapon.setVisible(false);
                    }
                }
            }

            int bulletY_coordinate = weapon.getY_coordinate();
            int bulletVelocity = 10;
            bulletY_coordinate -= bulletVelocity;

            int topBoundary = 0;
            if (bulletY_coordinate < topBoundary) {
                weapon.setVisible(false);
            } else {
                weapon.setY_coordinate(bulletY_coordinate);
            }
        }

        // Make enemies move and shoot
        Random generator = new Random();

        for (Enemy enemy : ENEMIES) {

            int shot = generator.nextInt(10);
            Missile missile = enemy.getMissile();
            int enemyY_coordinate = enemy.getY_coordinate();
            int enemyX_coordinate = enemy.getX_coordinate();

            if (shot == generator.nextInt(15) && enemy.isVisible() && missile.isDestroyed()) {
                missile.setDestroyed(false);
                missile.setX_coordinate(enemy.getX_coordinate() + 15);
                missile.setY_coordinate(enemy.getY_coordinate() + 20);
            }

            int missileX_coordinate = missile.getX_coordinate();
            int missileY_coordinate = missile.getY_coordinate();

            if (pilot.isVisible() && !missile.isDestroyed()) {

                if (missileX_coordinate >= pilotX_coordinate
                        && missileX_coordinate <= pilotX_coordinate + pilot.getImage().getWidth(null)
                        && missileY_coordinate >= pilotY_coordinate
                        && missileY_coordinate <= pilotY_coordinate + pilot.getImage().getHeight(null)) {
                    pilotHit();
                    missile.setDestroyed(true);
                }
            }

            if (!missile.isDestroyed()) {
                int missileVelocity = 2;
                missile.setY_coordinate(missile.getY_coordinate() + missileVelocity);

                int bottomBoundary = 950;
                if (missile.getY_coordinate() >= bottomBoundary) {
                    missile.setDestroyed(true);
                }
            }


            if (enemy.isVisible()) {
                int bottomBoundary = 1000;
                if (enemyY_coordinate > bottomBoundary) {
                    enemy.setVisible(false);
                }
            }

            if (pilot.isVisible() && !enemy.isDead()) {
                if (enemyX_coordinate >= pilotX_coordinate
                        && enemyX_coordinate <= pilotX_coordinate + pilot.getImage().getWidth(null)
                        && enemyY_coordinate >= pilotY_coordinate
                        && enemyY_coordinate <= pilotY_coordinate + pilot.getImage().getHeight(null)) {
                    pilotHit();
                    enemy.setDead(true);
                }
            }
            enemy.move();
        }
    }

    private void pilotHit() {
        life--;
        if (life == 0) {
            pilot.setImage(explosion);
            pilot.setDead(true);
        }
    }

    // Inner classes
    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runGame();
        }

        private void runGame() {
            update();
            repaint();
        }
    }

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            pilot.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            pilot.keyPressed(e);

            int pilotX_coordinate = pilot.getX_coordinate();
            int pilotY_coordinate = pilot.getY_coordinate();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (gameRunning) {
                    if (!weapon.isVisible()) {
                        weapon = new Weapon(pilotX_coordinate, pilotY_coordinate);
                    }
                }
            }
        }
    };
}
