package games.topgun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel  {
    final static int PANEL_WIDTH = 500;
    final static int PANEL_HEIGHT = 500;
    final static int NUM_ENEMIES = 4;
    private Dimension d;
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
    int yPilotStart = 450;
    int offset = PANEL_WIDTH / NUM_ENEMIES;
    final static String filepath = "img/";
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
        System.out.println(enemies.size());

        for(int i=0; i<NUM_ENEMIES; i++){
            enemies.add(new Enemy(x+offset*i,y));
        }
        System.out.println(enemies.size());
    }

    private void drawPilot(Graphics g) {

//        if (pilot.isVisible()) {

        g.drawImage(pilot.getImage(), pilot.getX_coordinate(), yPilotStart, this);
//        }
    }

    private void drawBackground(Graphics g){
        g.drawImage(backgroundImage,0,0,this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        doDrawing(g) ;

    }

    private void doDrawing(Graphics g) {
        drawBackground(g);
        drawPilot(g);

        for(Enemy enemy: enemies) { //location of the enemy planes + bullets at first
            drawEnemy(g,enemy.getX_coordinate());
            drawEnemyWeapon(g,enemy.getX_coordinate());
        }

    }

    public void update () {

        pilot.act();

       for(Enemy enemy: enemies) {
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

        /*    @Override
            public void actionPerformed(ActionEvent e) {
        //        moveEnemy();
                //moveEnemyShot();
                repaint();

            }*/

        /*    private static ArrayList<Enemy> getEnemiesArrayList() throws Exception {
                return enemies;
            }*/

    private void drawEnemy(Graphics g, int offset){
            for (Enemy enemy : enemies){

            if (enemy.isVisible()){
                g.drawImage(enemy.getImage(), offset, y, null);

            }
            else if(enemy.getY_coordinate() == PANEL_HEIGHT) {

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


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                //  if (inGame) {

                if (!weapon.isVisible()) {

                    weapon = new Weapon(x, yPilotStart);
                }
                //}
            }
        }
    }
}