package game.screenStates;

import game.GPanel;
import game.entity.NPC;

import java.awt.*;

public class Game {

    private GPanel gPanel;
    private DrawStates drawStates;
    private int gameState;

    private final int stop = 0;
    private final int play = 1;
    private final int dialog = 2;
    private final int home = 3;
    private final int functions = 4;
    private final int catchingAnimal = 5;
    private final int end = 6;
    private boolean victory = false;


    public Game(GPanel gPanel) {
        this.gPanel = gPanel;
        gameState = home;

        drawStates = new DrawStates(gPanel);
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

    public int getCatchingAnimal() {
        return catchingAnimal;
    }

    public int getEnd() {
        return end;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void teleport(){
        gPanel.getPlayer().setX(gPanel.getTileSize() * 23);
        gPanel.getPlayer().setY(gPanel.getTileSize() * 21);
        gameState = play;
    }

    public void setPlayersWalkThrough(){
        gPanel.getPlayer().setWalkThrough(true);
        gameState = play;
    }

    int count = 0;
   public void turnOffWalking() {
        if (gPanel.getPlayer().isWalkThrough()){
            if (count >= 600) {
                if (!gPanel.getPlayer().getCheckCollision().hasCollision(gPanel.getPlayer())){
                    gPanel.getPlayer().setWalkThrough(false);
                    count = 0;
                }
            }
            count++;
        }
    }


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

    public void setState(Graphics2D graphics2D){
        graphics2D.setColor(new Color(250,250,250));
        end();
        drawStates.drawlostCreatureCount(graphics2D);
        if (gameState == play){
        }
        if (gameState == stop) {
            drawStates.pauseScreen(graphics2D);
        }if (gameState == dialog){
            changeIndex();
            drawStates.dialogScreen(graphics2D);
        }if (gameState == home){
            drawStates.homeScreen(graphics2D);
        }if (gameState == functions){
            drawStates.funcScreen(graphics2D);
        }if (gameState == catchingAnimal){
            drawStates.animalsScreen(graphics2D);

        }if (gameState == end){
            drawStates.endScreen(graphics2D);
        }
    }

    public void end(){
       if (gPanel.getLostAnimals().size() == 0){
           victory = true;
           gameState = end;
       }
    }
}
