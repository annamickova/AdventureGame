package game;

import game.entity.NPC;
import game.items.Flower;
import game.items.Item;

public class Settings {
    GPanel gPanel;

    public Settings(GPanel gPanel) {
        this.gPanel = gPanel;
        setItems();
        setNPC();
    }

    public void setItems(){
        Flower flower = new Flower();
        flower.setItemX(gPanel.getTileSize()*21);
        flower.setItemY(gPanel.getTileSize()*23);
        gPanel.getItems().add(0, flower);

    }
    public void setNPC(){
        gPanel.getNpc().add(0, new NPC(gPanel));
        gPanel.getNpc().get(0).setX(gPanel.getTileSize()*21);
        gPanel.getNpc().get(0).setY(gPanel.getTileSize()*21);
    }
}
