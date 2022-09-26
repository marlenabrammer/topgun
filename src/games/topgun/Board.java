package games.topgun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {
    final static int PANEL_WIDTH = 500;
    final static int PANEL_HEIGHT = 500;
    final static int NUM_ENEMIES = 4;
    Enemy enemy;
    Pilot pilot;
    Weapon weapon;
    Image backgroundImage;
    Timer timer;
    int xVelocity =1;
    int yVelocity =3;
    int yWeaponVelocity = 5;
    int x =0;
    int y =0;
    int y_weapon = y+45;
    int xPilotStart = 250;
    int yPilotStart = 450;
    int offset = PANEL_WIDTH / NUM_ENEMIES;
    final static String filepath = "img/";
    private static ArrayList<Enemy> enemies = new ArrayList<>();


    Board(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        enemy = new Enemy(0,0);
        enemies.add(enemy);
        enemies.add(new Enemy(0,0));
        enemies.add(new Enemy(0,0));
        pilot = new Pilot();
        backgroundImage = new ImageIcon(filepath + "terrain.jpg").getImage();
        timer = new Timer(100,this );
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g); //this will paint the background
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage, 0,0,null);
        //g2D.drawImage(enemy.getImage(), x,y,null);
        g2D.drawImage(pilot.getImage(), xPilotStart,yPilotStart,null);
       // g2D.drawImage(weapon.getImage(),offset,0,null);
        for(int i=0; i<NUM_ENEMIES; i++) { //location of the enemy planes + bullets at first
                drawEnemy(g2D,x+offset*i);
                drawEnemyWeapon(g2D,x+offset*i);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveEnemy();
        //moveEnemyShot();
        repaint();

    }

    private static ArrayList<Enemy> getEnemiesArrayList() throws Exception {
        return enemies;
    }

    private void drawEnemy(Graphics g, int offset){
        for (Enemy enemy : enemies){

            if (enemy.isVisible()){
                g.drawImage(enemy.getImage(), offset, y, null);

            }
            else if(enemy.getY_coordinate() == PANEL_HEIGHT)
            {

            }
            if(enemy.isDying()){
                enemy.die();
            }
        }
    }

    private void drawEnemyWeapon(Graphics g, int offset) {
        for (Enemy enemy : enemies) {
            Enemy.Bomb weapon = enemy.getWeapon();
            if (enemy.isVisible()) {
                g.drawImage(weapon.getImage(), offset, y+enemy.getImage().getHeight(null), null);
            }
        }
    }

//    private void update(){
//        //call the pilot
//        //call the enemies
//        //call the initial weapons
//        //do logic for bullet from enemy hitting pilot
//        //do logic for bullet from pilot hitting enemy
//
//    }

    private void moveEnemy(){
        y += yVelocity;
    }

    private void moveEnemyShot(){
        y_weapon+= yWeaponVelocity;
    }

    private void movePlane(){
        if (x>=PANEL_WIDTH-pilot.getImage().getWidth(null) || x< 0){ //so that it doesn't go off the border
            xVelocity = xVelocity * -1;
        } //goes back and forth
        x += xVelocity;
        repaint();
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
            int y = pilot.getY_coordinate();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                //  if (inGame) {

                if (!weapon.isVisible()) {

                    weapon = new Weapon(x, y);
                }
                //}
            }
        }
    }
}
