package game;

import game.background.Background;
import game.entity.*;
import game.items.Item;
import game.screenStates.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GPanel extends JPanel implements Runnable{

    private Game game;
    private final int tileSize;
    private int screenWidth;
    private int screenHeight;
    private int FPS;
    private Thread thread;
    private KeyHandler keyHandler;
    private Player player;
    private ArrayList<Item> items;
    private ArrayList<Item> collectedItems;
    private ArrayList<Creature> lostCreatures;
    private ArrayList<Creature> caughtCreatures;
    private Enemy enemy;
    private ArrayList<NPC> npc;
    private Setting setting;
    private Background bGround;
    private int maxCol;
    private int maxRow;
    private boolean running;
    private int currDialogIndex;

    public ArrayList<Creature> getLostAnimals() {
        return lostCreatures;
    }

    public ArrayList<Creature> getCaughtAnimals() {
        return caughtCreatures;
    }

    public Game getGame() {
        return game;
    }

    public void setCurrDialogIndex(int currDialogIndex) {
        this.currDialogIndex = currDialogIndex;
    }

    public int getCurrDialogIndex() {
        return currDialogIndex;
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

    public ArrayList<Item> getCollectedItems() {
        return collectedItems;
    }

    public ArrayList<NPC> getNpc() {
        return npc;
    }

    public Setting getSettings() {
        return setting;
    }

    public GPanel(){
        tileSize = 48;
        keyHandler = new KeyHandler(this);
        game = new Game(this);
        currDialogIndex = 0;
        running = true;
        setPanelSize();
        setPlayersStuff();
        setAssets();
        setgPanelSettings();
    }

    private void setPlayersStuff(){
        player = new Player(this, keyHandler);
    }

    private void setAssets(){
        try {
            bGround = new Background(this);
            items = new ArrayList<>();
            collectedItems = new ArrayList<>();
            lostCreatures = new ArrayList<>();
            caughtCreatures = new ArrayList<>();
            npc = new ArrayList<>();
            enemy = new Enemy(this);
            setting = new Setting(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Creating new thread. Game is running until thread is not null.
     */
    public void startGame(){
        if (thread == null || !running){
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Updates player, enemy and npc.
     */
    private void update(){
        if (game.getGameState() == game.getPlay()){
            player.update();
            game.turnOffWalking();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (Creature lostCreature : lostCreatures) {
                if (lostCreature != null) {
                    lostCreature.update();
                }
            }
            enemy.update();
        }
    }

    /**
     * Painting all game components.
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        if (game.getGameState() == game.getHome()){
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
            for (Creature lostCreature : lostCreatures) {
                if (lostCreature != null) {
                    lostCreature.drawEntity(graphics2D);
                }
            }
            enemy.drawEntity(graphics2D);
            enemy.getGuard().drawEntity(graphics2D);
            player.drawPlayer(graphics2D);
            game.setState(graphics2D);
        }
        graphics2D.dispose();
    }

    /**
     * Sleep method.
     */
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
