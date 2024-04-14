import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel implements Runnable{


    // screen settings
    private int tileSize;
    private int screenWidth;
    private int screenHeight;

    Thread thread;


    public GPanel(){
        this.tileSize = 48;
        this.screenWidth = tileSize * 18;
        this.screenHeight = tileSize * 14;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }

    public void startThread(){
        thread = new Thread();
    }

    @Override
    public void run() {

    }
}
