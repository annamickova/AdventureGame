package game;

import game.background.Background;
import game.entity.*;
import game.items.Fuel;
import game.items.Item;
import game.screenStates.Game;
import game.screenStates.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GPanel extends JPanel implements Runnable{

    private final Game game;
    private final int tileSize;
    private int screenWidth;
    private int screenHeight;
    private int FPS;
    private Thread thread;
    private final KeyHandler keyHandler;
    private Player player;
    private ArrayList<Item> functionItems;
    private ArrayList<Item> collectedFunctionItems;
    private ArrayList<Fuel> collectedItems;
    private ArrayList<Fuel> lostItems;

    public ArrayList<Fuel> getCollectedItems() {
        return collectedItems;
    }
    public ArrayList<Fuel> getLostItems() {
        return lostItems;
    }

    private ArrayList<Creature> creatures;
    private Enemy enemy;
    private ArrayList<NPC> npc;
    private Setting setting;
    private Background bGround;
    private int maxCol;
    private int maxRow;
    private final boolean isRunning;
    private int currDialogIndex;

    public ArrayList<Creature> getCreatures() {
        return creatures;
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

    public ArrayList<Item> getFunctionItems() {
        return functionItems;
    }

    public ArrayList<Item> getCollectedFunctionItems() {
        return collectedFunctionItems;
    }

    public ArrayList<NPC> getNpc() {
        return npc;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public GPanel(){
        tileSize = 48;
        game = new Game(this);
        keyHandler = new KeyHandler(this);
        currDialogIndex = 0;
        isRunning = true;
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
            functionItems = new ArrayList<>();
            collectedFunctionItems = new ArrayList<>();
            collectedItems = new ArrayList<>();
            lostItems = new ArrayList<>();
            creatures = new ArrayList<>();
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
        if (thread == null || !isRunning){
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Updates player, enemy and npc.
     */
    private void update(){
        if (game.getGameState() == GameState.PLAY){
            player.update();
            game.checkFunctions();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (Creature lostCreature : creatures) {
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

        if (game.getGameState() == GameState.HOME){
            game.setState(graphics2D);
        }else {
            bGround.draw(graphics2D);
            for (Item item : functionItems) {
                if (item != null) {
                    if (item.isVisible()){
                        item.drawItem(graphics2D, this);
                    }
                }
            }   for (Item item : lostItems) {
                if (item != null) {
                    item.drawItem(graphics2D, this);
                }
            }
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.drawEntity(graphics2D);
                }
            }
            for (Creature lostCreature : creatures) {
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

        while (isRunning){
            update();
            repaint();
            game.timer();
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
