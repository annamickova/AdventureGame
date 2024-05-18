package game;

import game.entity.Cat;
import game.entity.NPC;
import game.items.FlowerTeleport;
import game.items.Walk;

public class Settings {
    GPanel gPanel;

    public Settings(GPanel gPanel) {
        this.gPanel = gPanel;
        setItems();
        setNPC();
    }


    private void setItems(){
        FlowerTeleport flowerTeleport = new FlowerTeleport(gPanel);
        flowerTeleport.setName("teleport");
        flowerTeleport.setItemX(gPanel.getTileSize()*21);
        flowerTeleport.setItemY(gPanel.getTileSize()*23);
        gPanel.getItems().add(0, flowerTeleport);

        Walk walk = new Walk(gPanel);
        walk.setName("walk through");
        walk.setItemX(gPanel.getTileSize()*21);
        walk.setItemY(gPanel.getTileSize()*20);
        gPanel.getItems().add(1, walk);
    }

    public void collectItem(){
        for (int i = 0; i < gPanel.getItems().size(); i++) {
            if (gPanel.getItems().get(i).itemArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getCollectedItems().add(0, gPanel.getItems().get(i));
                gPanel.getItems().remove(gPanel.getItems().get(i));
            }
        }
    }
    private void setNPC(){
        NPC npc1 = new Cat(gPanel);
        npc1.setX(gPanel.getTileSize()*21);
        npc1.setY(gPanel.getTileSize()*21);
        gPanel.getNpc().add(npc1);

        NPC npc2 = new Cat(gPanel);
        npc2.setX(gPanel.getTileSize()*20);
        npc2.setY(gPanel.getTileSize()*39);
        npc2.setName("haf");
        gPanel.getNpc().add(npc2);
    }


}
