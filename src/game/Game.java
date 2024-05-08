package game;

import game.entity.NPC;

import java.awt.*;

public class Game {

    GPanel gPanel;

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
}
