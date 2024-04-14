import javax.swing.*;
import java.awt.*;

public class GFrame extends JFrame {

    public GFrame(GPanel gPanel) throws HeadlessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Adventure game");
        this.add(gPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
