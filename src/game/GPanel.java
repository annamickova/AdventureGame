package game;

import game.background.Background;
import game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel implements Runnable{


    // screen settings
    private final int tileSize = 48;
    private int screenWidth;
    private int screenHeight;
    private int FPS;

    Thread thread;
    KeyHandler keyHandler = new KeyHandler();

 // player
    private Player player = new Player(this, keyHandler);
    private int speed;
    private Background bGround;
    private int maxCol;
    private int maxRow;
    private boolean running;

    public Background getbGround() {
        return bGround;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxCol() {
        return maxCol;
    }


    public int getMaxRow() {
        return maxRow;
    }

    public Player getPlayer() {
        return player;
    }

    public GPanel(){
        this.running = true;
        this.maxRow = 60;
        this.maxCol = 60;

        this.screenWidth = tileSize * 16;
        this.screenHeight = tileSize * 16;
        this.FPS = 60;

        this.speed = 5;
        this.bGround = new Background(this);


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setLayout(null);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGame(){
        if (thread == null || !running){
            thread = new Thread(this);
            thread.start();
        }
    }
    public void stopGame(){
        running = false;
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

        bGround.draw(graphics2D);
        player.draw(graphics2D);
//        graphics2D.setPaint(Color.WHITE);
//        graphics2D.fillRect(playerX,playerY, tileSize, tileSize);

        graphics2D.dispose();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (running){
            update();
            repaint();

            try {
                double remainingT = nextDrawTime - System.nanoTime();
                remainingT = remainingT/1000000;

                if (remainingT < 0){
                    remainingT = 0;
                }

                Thread.sleep((long) remainingT);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
