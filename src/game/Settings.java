package game;

import game.entity.NPC;
import game.items.Flower;

public class Settings {
    GPanel gPanel;

    public Settings(GPanel gPanel) {
        this.gPanel = gPanel;
        setItems();
        setNPC();
    }


    public void setItems(){
        Flower flower = new Flower(gPanel);
        flower.setItemX(gPanel.getTileSize()*21);
        flower.setItemY(gPanel.getTileSize()*23);
        gPanel.getItems().add(0, flower);
    }

    public void collectItem(){
        for (int i = 0; i < gPanel.getItems().size(); i++) {
            if (gPanel.getItems().get(i).itemArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getItems().remove(gPanel.getItems().get(i));

            }
        }
    }
    public void setNPC(){
        gPanel.getNpc().add(0, new NPC(gPanel));
        gPanel.getNpc().get(0).setX(gPanel.getTileSize()*21);
        gPanel.getNpc().get(0).setY(gPanel.getTileSize()*21);
    }


}
