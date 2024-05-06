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


    // screen settings
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
    private int play = 1;
    private int stop = 0;
    private int dialog = 2;
    private DrawStates drawStates;
    private int currDialogIndex;

    public int getCurrDialogIndex() {
        return currDialogIndex;
    }

    public void setCurrDialogIndex(int currDialogIndex) {
        this.currDialogIndex = currDialogIndex;
    }

    public DrawStates getDrawStates() {
        return drawStates;
    }

    public int getDialog() {
        return dialog;
    }

    public void setDialog(int dialog) {
        this.dialog = dialog;
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

    public ArrayList<NPC> getNpc() {
        return npc;
    }

    public void setNpc(ArrayList<NPC> npc) {
        this.npc = npc;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public GPanel(){
        keyHandler = new KeyHandler(this);
        currDialogIndex = 0;
        gameState = play;
        running = true;

        setPanelSize();

        setPlayersStuff();
        setAssets();
        gPanelSettings();
    }

    private void setPlayersStuff(){
        player = new Player(this, keyHandler);
        speed = 5;
    }

    private void setAssets(){
        bGround = new Background(this);
        settings = new Settings(this);
        items = new ArrayList<>();
        npc = new ArrayList<>();
        drawStates = new DrawStates(this);
    }

    private void setPanelSize(){
        maxRow = 60;
        maxCol = 60;
        screenWidth = tileSize * 16;
        screenHeight = tileSize * 16;
        FPS = 60;
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
        setState(graphics2D);

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

    private void setState(Graphics2D graphics2D){
        graphics2D.setColor(new Color(250,250,250));
        if (gameState == play){

        }
        if (gameState == stop ) {
            drawStates.pauseScreen(graphics2D);

        }if (gameState == dialog){
            changeIndex();
            drawStates.dialogScreen(graphics2D);
        }
    }

    public void changeIndex(){
        int n = 0;
        int howMany = 0;
        for (NPC npc : npc) {
            if (npc.getDialogIndex() == currDialogIndex + 1) {
                n++;
            }
            if (npc.getDialogues().size() > howMany) {
                howMany = npc.getDialogues().size() - 1;
            }
        }
        if (n == npc.size()){
            if (currDialogIndex <= howMany-1){
                currDialogIndex++;
            }

        }
    }







}
