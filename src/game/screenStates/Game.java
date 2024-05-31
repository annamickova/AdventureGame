package game.screenStates;

import game.GPanel;
import game.entity.NPC;

import java.awt.*;

public class Game {
    private final GPanel gPanel;
    private final DrawStates drawStates;
    private int gameState;
    private int count = 0;

    private final int stop = 0;
    private final int play = 1;
    private final int dialog = 2;
    private final int home = 3;
    private final int functions = 4;

    private final int isEnd = 6;
    private boolean isVictory = false;
    private final int description = 7;
    private int fuelNeed = 10;
    private boolean canLostLives = true;


    public Game(GPanel gPanel) {
        this.gPanel = gPanel;
        gameState = home;

        drawStates = new DrawStates(gPanel);
    }

    public int getFuelNeed() {
        return fuelNeed;
    }

    public void setFuelNeed(int fuelNeed) {
        this.fuelNeed = fuelNeed;
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
    public DrawStates getDrawStates() {
        return drawStates;
    }

    public int getStop() {
        return stop;
    }
    public int getDialog() {
        return dialog;
    }
    public int getHome() {
        return home;
    }
    public int getFunctions() {
        return functions;
    }
    public int getIsEnd() {
        return isEnd;
    }

    public boolean isVictory() {
        return isVictory;
    }

    public int getDescription() {
        return description;
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
           if (count == 30) {
              canLostLives = true;
              count = 0;
           }
           count++;
       } else if (gPanel.getPlayer().getCheckCollision().hit(gPanel.getPlayer(), gPanel.getEnemy())) {
           if (canLostLives){
               gPanel.getPlayer().setLives(gPanel.getPlayer().getLives()-2);
           }
           canLostLives = false;
           if (count == 30) {
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
        drawStates.drawlostItemsCount(graphics2D);
        switch (gameState){
            case stop -> drawStates.pauseScreen(graphics2D);
            case dialog -> {
                changeIndex();
                drawStates.dialogScreen(graphics2D);
            }
            case home -> drawStates.homeScreen(graphics2D);
            case functions -> drawStates.funcScreen(graphics2D);
            case description -> drawStates.gameDescriptionScreen(graphics2D);
            case isEnd -> drawStates.gameOverScreen(graphics2D);
        }
    }
    /**
     * Game over when player catches all creatures.
     */
    public void end(){
       if (fuelNeed == 0){
           isVictory = true;
           gameState = isEnd;
       } else if (gPanel.getPlayer().getLives() == 0) {
           gameState = isEnd;
       }
    }
}
