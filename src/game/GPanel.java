package game;

import game.background.Background;
import game.entity.Entity;
import game.entity.NPC;
import game.entity.Player;
import game.items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GPanel extends JPanel implements Runnable{

    Game game = new Game(this);
    private final int tileSize = 48;
    private int screenWidth;
    private int screenHeight;
    private int FPS;

    Thread thread;
    KeyHandler keyHandler;

    private Player player;
    private ArrayList<Item> items;
    private ArrayList<NPC> npc;
    private Settings settings;
    private int speed;
    private Background bGround;
    private int maxCol;
    private int maxRow;
    private boolean running;

    private int gameState;
    private final int stop = 0;
    private final int play = 1;
    private final int dialog = 2;
    private final int home = 3;

    private int currDialogIndex;

    public Game getGame() {
        return game;
    }

    public void setCurrDialogIndex(int currDialogIndex) {
        this.currDialogIndex = currDialogIndex;
    }

    public int getHome() {
        return home;
    }

    public int getCurrDialogIndex() {
        return currDialogIndex;
    }



    public int getDialog() {
        return dialog;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlay() {
        return play;
    }

    public int getStop() {
        return stop;
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
    public ArrayList<NPC> getNpc() {
        return npc;
    }

    public Settings getSettings() {
        return settings;
    }

    public GPanel(){
        keyHandler = new KeyHandler(this);
        currDialogIndex = 0;
        gameState = play;
        running = true;

        setPanelSize();

        setPlayersStuff();
        setAssets();
        setgPanelSettings();
    }

    private void setPlayersStuff(){
        player = new Player(this, keyHandler);
        speed = 5;
    }

    private void setAssets(){
        bGround = new Background(this);
        items = new ArrayList<>();
        npc = new ArrayList<>();
        settings = new Settings(this);

    }

    private void setPanelSize(){
        maxRow = 60;
        maxCol = 60;
        screenWidth = tileSize * 16;
        screenHeight = tileSize * 16;
        FPS = 60;
    }
    private void setgPanelSettings(){
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
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        if (gameState == home){
            game.setState(graphics2D);
        }else {
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
            game.setState(graphics2D);
        }
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
