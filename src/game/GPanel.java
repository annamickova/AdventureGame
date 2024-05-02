package game;

import game.background.Background;
import game.entity.Entity;
import game.entity.Player;
import game.items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GPanel extends JPanel implements Runnable{


    // screen settings
    private final int tileSize = 48;
    private int screenWidth;
    private int screenHeight;
    private int FPS;

    Thread thread;
    KeyHandler keyHandler = new KeyHandler(this);

    private Player player = new Player(this, keyHandler);
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Entity> npc = new ArrayList<>();
    private Settings settings = new Settings(this);
    private int speed;
    private Background bGround;
    private int maxCol;
    private int maxRow;
    private boolean running;

    private int gameState;
    private int play = 1;
    private int stop = 0;

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Entity> getNpc() {
        return npc;
    }

    public void setNpc(ArrayList<Entity> npc) {
        this.npc = npc;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public GPanel(){
        gameState = play;
        running = true;
        maxRow = 60;
        maxCol = 60;

        screenWidth = tileSize * 16;
        screenHeight = tileSize * 16;
        FPS = 60;

        speed = 5;
        bGround = new Background(this);
        settings.setNPC();
        gPanelSettings();
    }
    private void gPanelSettings(){
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

    private void update(){
        if (gameState == play){
            player.update();
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        bGround.draw(graphics2D);
        for (Item item : items) {
            if (item != null) {
                item.drawItem(graphics2D, this);
            }
        }
        for (Entity entity : npc) {
            if (entity != null) {
                entity.drawEntity(graphics2D);
            }
        }
        player.draw(graphics2D);
        pause(graphics2D);

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

    private void pause(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        if (gameState == play){
        }
        if (gameState == stop ) {
            pauseScreen(graphics2D);
        }
    }

    private void pauseScreen(Graphics2D graphics2D){
        String text = "PAUSED";
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 60));
        int l = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = screenWidth/2 - l/2;
        int y = screenHeight/2 - tileSize;

        graphics2D.drawString(text, x, y);
    }





}
