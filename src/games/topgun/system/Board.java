package games.topgun.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {
    final static int PANEL_WIDTH = 500;
    final static int PANEL_HEIGHT = 500;
    final static int NUM_ENEMIES = 4;
    Image enemy;
    Image pilot;
    Image backgroundImage;
    Timer timer;
    int xVelocity =1;
    int yVelocity =1;
    int x =0;
    int y =0;
    int xPilotStart = 250;
    int yPilotStart = 450;
    int offset = PANEL_WIDTH / NUM_ENEMIES;
    final static String filepath = "img/";


    Board(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        enemy = new ImageIcon(filepath + "enemy.png").getImage();
        pilot = new ImageIcon(filepath + "pilot.png").getImage();
        backgroundImage = new ImageIcon(filepath + "terrain.jpg").getImage();
        timer = new Timer(100,this );
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g); //this will paint the background
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage, 0,0,null);
        //  g2D.drawImage(enemy, x,y,null);
        g2D.drawImage(pilot, xPilotStart,yPilotStart,null);
        for(int i=0; i<NUM_ENEMIES; i++) {
            if(i==0){
                drawEnemy(g2D,x);
            }
            else{
                int placement = x + offset;
                drawEnemy(g2D,placement);

            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveEnemy();
        //  movePlane();
    }

    private static ArrayList<Image> getImagesArrayList() throws Exception {
        ArrayList<Image> images = new ArrayList<>();
        for(int i =0; i<NUM_ENEMIES; i++){
            Image enemy =  new ImageIcon(filepath+"enemy.png").getImage();
            images.add(enemy);
        }
        return images;
    }

    private void drawEnemy(Graphics g, int offset){
        g.drawImage(enemy, offset, y, null);
    }
    private void moveEnemy(){
        if (y>=PANEL_HEIGHT-enemy.getWidth(null)){ // if you don't want it to go off page do || y<0so that it doesn't go off the border
            yVelocity = yVelocity * -1;
        } //goes up and down
        y += yVelocity;
        repaint();
    }

    private void movePlane(){
        if (x>=PANEL_WIDTH-pilot.getWidth(null) || x< 0){ //so that it doesn't go off the border
            xVelocity = xVelocity * -1;
        } //goes back and forth
        x += xVelocity;
        repaint();
    }
}
