package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GFrame extends JFrame {
    GPanel gPanel;
    public GFrame() throws HeadlessException {
        gPanel = new GPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Adventure game");
        gPanel.setLayout(null);
        frame.add(gPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gPanel.startGame();

    }

}

