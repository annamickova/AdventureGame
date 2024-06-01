package game.screenStates;

import game.GPanel;
import game.entity.NPC;

import java.awt.*;
import java.io.*;

public class Game {
    private final GPanel gPanel;
    private final DrawStates draw;
    private GameState gameState;
    private int count = 0;
    private boolean isVictory = false;
    private int fuelNeed = 10;
    private boolean canLostLives = true;
    private int minutes = 0;
    private int seconds = 0;
    private long startTime;
    private long t;
    private int score = 0;
    private boolean scoreWritten = false;

    public Game(GPanel gPanel) {
        this.gPanel = gPanel;
        gameState = GameState.HOME;

        draw = new DrawStates(gPanel);
    }

    public int getFuelNeed() {
        return fuelNeed;
    }

    public void setFuelNeed(int fuelNeed) {
        if (fuelNeed > 0){
            this.fuelNeed = fuelNeed;
        }else {
            this.fuelNeed = 0;
        }

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public DrawStates getDraw() {
        return draw;
    }

    public boolean isVictory() {
        return isVictory;
    }

    public int getScore() {
        return score;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Turning off walking through different tiles ability when player is not on tile with collision.
     */
   private void turnOffFlying() {
        if (gPanel.getPlayer().isFly()){
            if (count >= 600) {
                if (!gPanel.getPlayer().getCheckCollision().hasCollision(gPanel.getPlayer())){
                    gPanel.getPlayer().setFly(false);
                    count = 0;
                }
            }
            count++;
        }
    }

    /**
     * Setting player`s speed back to normal speed.
     */
    private void higherSpeed(){
        if (gPanel.getPlayer().isHighSpeed()){
            gPanel.getPlayer().setSpeed(6);
            if (count == 600) {
                gPanel.getPlayer().setHighSpeed(false);
                gPanel.getPlayer().setSpeed(4);
                count = 0;
            }
            count++;
        }
   }

    /**
     * Turning off swimming ability.
     */
   private void swim(){
       if (gPanel.getPlayer().isSwimmming()){
           if (count >= 420) {
               if (!gPanel.getPlayer().getCheckCollision().hasCollision(gPanel.getPlayer())){
                   gPanel.getPlayer().setSwimmming(false);
                   count = 0;
               }
           }
           count++;
       }
   }

    /**
     * Removes players live if he meets npc. When he meets enemy he takes 2 his lives.
     */
   private void loseLives(){
       if (gPanel.getPlayer().getCheckCollision().playerHitEntity()){
           if (canLostLives){
               gPanel.getPlayer().setLives(gPanel.getPlayer().getLives()-1);
           }
           canLostLives = false;
           if (count == 90) {
              canLostLives = true;
              count = 0;
           }
           count++;
       } else if (gPanel.getPlayer().getCheckCollision().hit(gPanel.getPlayer(), gPanel.getEnemy())) {
           if (canLostLives){
               gPanel.getPlayer().setLives(gPanel.getPlayer().getLives()-2);
           }
           canLostLives = false;
           if (count == 90) {
               canLostLives = true;
               count = 0;
           }
           count++;
       }
   }

    /**
     * Checking all functions.
     */
   public void checkFunctions(){
       loseLives();
       turnOffFlying();
       higherSpeed();
       swim();
   }

    /**
     * Checking and increasing npc dialog index.
     */
    public void changeIndex(){
        int n = 0;
        int howMany = 0;
        for (NPC npc : gPanel.getNpc()) {
            if (npc.getDialogIndex() == gPanel.getCurrDialogIndex() + 1) {
                    n++;
            }
            if (npc.getDialogues().size() > howMany) {
                    howMany = npc.getDialogues().size() - 1;
            }
        }
        if (n == gPanel.getNpc().size()){
            if (gPanel.getCurrDialogIndex() <= howMany-1){
                gPanel.setCurrDialogIndex(gPanel.getCurrDialogIndex()+1);
            }
        }
    }

    /**
     * Deciding what to display by game state.
     * @param graphics2D
     */
    public void setState(Graphics2D graphics2D){
        graphics2D.setColor(new Color(250,250,250));
        end();
        draw.currTime(graphics2D);
        draw.lostFuel(graphics2D);
        switch (gameState){
            case STOP -> draw.pauseScreen(graphics2D);
            case DIALOG -> {
                changeIndex();
                draw.dialogScreen(graphics2D);
            }
            case HOME -> draw.homeScreen(graphics2D);
            case FUNCTIONS -> draw.funcScreen(graphics2D);
            case DESCRIPTION -> draw.gameDescriptionScreen(graphics2D);
            case END -> {
                draw.gameOverScreen(graphics2D);
                if (!scoreWritten){
                    writeScore("assets/scoreHistory.txt");
                    scoreWritten = true;
                }
            }
        }
    }
    /**
     * Game over when player catches all creatures or loses all lives.
     */
    public void end(){
       if (fuelNeed == 0){
           isVictory = true;
           gameState = GameState.END;
       } else if (gPanel.getPlayer().getLives() == 0) {
           gameState = GameState.END;
       }
       updateScore();
    }

    /**
     * Calculating minutes and second since game started.
     */
    public void timer(){
        if (gameState == GameState.PLAY){
            long currentTime = System.nanoTime();
            t = (currentTime - startTime) / 1000000000;
            minutes = (int) (t / 60);
            seconds = (int) (t % 60);
        }
    }

    /**
     * Counting score based on player's time and his lost lives.
     */
    public void updateScore(){
        int num = (int) (1000 - t) - (3-gPanel.getPlayer().getLives())*20;
        score = Math.max(num, 0);
    }

    /**
     * Comparing new high score.
     * @return
     */
    public int newScore(){
        int highest = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("assets/scoreHistory.txt"))){
            String line;
            while ((line = br.readLine())!= null) {
                int score = Integer.parseInt(line.trim());
                if (score > highest) {
                    highest = score;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return highest;
    }

    /**
     * Writing new score into text file.
     * @param fileName
     */
    public void writeScore(String fileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
            bw.write(score+"");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
