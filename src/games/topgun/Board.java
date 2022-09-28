package games.topgun;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel  {
    private final static int PANEL_WIDTH = 500;
    private final static int PANEL_HEIGHT = 500;
    private final static int NUM_ENEMIES = 4;
    private Dimension d;
    private Boolean playingGame = true;
    private int enemiesUnalived = 0;
    private Pilot pilot;
    private Weapon weapon;
    private Image backgroundImage;
    private Image explosion;
    private Image gameOverImage;
    private Timer timer;
    private int xVelocity =1;
    private int x =0;
    private int y =0;
    private int offset = PANEL_WIDTH / NUM_ENEMIES;
    private final static String filepath = "img/";
    private static ArrayList<Enemy> enemies = new ArrayList<>();


    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        timer = new Timer(15, new GameCycle());
        gameInit();

        timer.start();


    }

    private void gameInit() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        pilot = new Pilot();
        backgroundImage = new ImageIcon(filepath + "terrain.jpg").getImage();
        weapon = new Weapon();
        //create enemies
        for(int i=0; i<NUM_ENEMIES; i++){
            enemies.add(new Enemy(x+offset*i,y));
        }
        System.out.println("Size of the enemies array is: " + enemies.size());
    }

    private void drawPilot(Graphics g) {

        if (pilot.isVisible()) {

        g.drawImage(pilot.getImage(), pilot.getX_coordinate(), pilot.getY_coordinate(), this);
        }
    }

    private void drawPilotWeapon(Graphics g){
        if(weapon.isVisible()) {
            g.drawImage(weapon.getImage(), weapon.getX_coordinate(), weapon.getY_coordinate(), null);
        }
    }

    private void drawBackground(Graphics g){
        g.drawImage(backgroundImage,0,0,this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g) ;

    }

    private void doDrawing(Graphics g) {
        if(playingGame) {
            drawBackground(g);
            drawPilot(g);
            drawPilotWeapon(g);

            for (Enemy enemy : enemies) { //location of the enemy planes + bullets at first
                drawEnemy(g, enemy.getX_coordinate());
                drawEnemyWeapon(g, enemy.getX_coordinate());
            }
        }
        else {
            if(timer.isRunning()){
                timer.stop();
            }
            gameOver(g);
        }

    }

    private void gameOver(Graphics g) {
        gameOverImage = new ImageIcon(filepath + "terrain.jpg").getImage();
        g.drawImage(gameOverImage, 0,0,null);

    }

    public void update () {

        if (enemiesUnalived == NUM_ENEMIES) {

            playingGame = false;
            timer.stop();
            System.out.println("game is stopped");
           // message = "Game won!";
        }

        pilot.move();

        if (weapon.isVisible()) {

            int weaponX = weapon.getX_coordinate();
            int weaponY = weapon.getY_coordinate();

            for(Enemy enemy: enemies){

                int enemyX = enemy.getX_coordinate();
                int enemyY = enemy.getY_coordinate();

                if(enemy.isVisible() && weapon.isVisible()){
                    //create impact boundary
                    if(weaponX >= (enemyX)
                            && weaponX <= (enemyX +enemy.getImage().getWidth(null))
                            && weaponY >= (enemyY)
                            && weaponY <= (enemyY + enemy.getImage().getHeight(null)))
                    {
                        explosion = new ImageIcon(filepath+ "bang.png").getImage();
                        enemy.setImage(explosion);
                        enemy.setDead(true);
                        enemiesUnalived++;
                        System.out.println("count of dead enemies: " + enemiesUnalived);
                        weapon.die();
                    }
                }
            }

            int y = weapon.getY_coordinate();
            y -= 4;
            if (y < 0) {
                weapon.die();
             //   System.out.println("weapon goes off screen");
            } else {
                weapon.setY_coordinate(y);
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy.isVisible()) {

                int y = enemy.getY_coordinate();

                if (y > 500) {
                    enemy.die();
                }
            }
            enemy.move();
        }
    }
    private void doGameCycle () {
        update();
        repaint();
    }
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed (ActionEvent e) {
            doGameCycle();
        }
    }

    private void drawEnemy(Graphics g, int offset){
            for (Enemy enemy : enemies){
            if (enemy.isVisible()){
                g.drawImage(enemy.getImage(), offset, y, null);
            }

            if(enemy.isDead()){
                 enemy.die();
                    }
                }
            }


    private void drawEnemyWeapon(Graphics g, int offset) {
        for (Enemy enemy : enemies) {
            Enemy.Missile weapon = enemy.getMissile();
            if (enemy.isVisible()) {
                g.drawImage(weapon.getImage(), offset, y+enemy.getImage().getHeight(null), null);
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            pilot.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            pilot.keyPressed(e);

            int x = pilot.getX_coordinate();


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                //  if (inGame) {

                if (!weapon.isVisible()) {

                    weapon = new Weapon(x, pilot.getY_coordinate());
                }
                //}
            }
        }
    }
}