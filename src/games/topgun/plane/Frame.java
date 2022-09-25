package games.topgun.plane;

import javax.swing.*;

public class Frame extends JFrame {

    Board panel;

    Frame(){
        panel = new Board();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
