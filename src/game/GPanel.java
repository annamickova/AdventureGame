package game;

import game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel implements Runnable{


    // screen settings
    private int tileSize;
    private int screenWidth;
    private int screenHeight;
    private int FPS;

    Thread thread;
    KeyHandler keyHandler = new KeyHandler();

 // player
    private Player player = new Player(this, keyHandler);
    private int speed;

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public GPanel(){
        this.tileSize = 48;
        this.screenWidth = tileSize * 18;
        this.screenHeight = tileSize * 14;
        this.FPS = 60;

        this.speed = 5;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    public void update(){
        player.update();

//        if (keyHandler.upPress){
//            playerY -= speed;
//        } else if (keyHandler.downPress) {
//            playerY += speed;
//        } else if (keyHandler.leftPress) {
//            playerX -= speed;
//        }else if (keyHandler.rightPress){
//            playerX += speed;
//        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;


         player.draw(graphics2D);
//        graphics2D.setPaint(Color.WHITE);
//        graphics2D.fillRect(playerX,playerY, tileSize, tileSize);

        graphics2D.dispose();
    }

    @Override
    public void run() {

        double interval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while (thread != null){
            currTime = System.nanoTime();
            delta += (currTime - lastTime)/interval;
            lastTime = currTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

}
