package games.topgun;

import javax.swing.*;

public class Frame extends JFrame {

    Board panel;

    public Frame(){
        panel = new Board();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel); //adds Board to the frame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
