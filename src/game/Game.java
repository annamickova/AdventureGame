package game;

import game.entity.NPC;

import java.awt.*;

public class Game {

    private GPanel gPanel;

    public Game(GPanel gPanel) {
        this.gPanel = gPanel;
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
        if (gPanel.getGameState() == gPanel.getHome()){

        }
        if (gPanel.getGameState() == gPanel.getPlay()){

        }
        if (gPanel.getGameState() == gPanel.getStop() ) {
            gPanel.getDrawStates().pauseScreen(graphics2D);

        }if (gPanel.getGameState() == gPanel.getDialog()){
            changeIndex();
            gPanel.getDrawStates().dialogScreen(graphics2D);
        }
    }
}
