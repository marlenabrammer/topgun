package games.topgun.app;

import games.topgun.Board;
import games.topgun.Board2;

import javax.swing.*;

public class Frame extends JFrame {

    Board2 panel;

    public Frame(){
        panel = new Board2();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel); //adds Board to the frame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
