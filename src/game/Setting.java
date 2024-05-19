package game;

import game.entity.Animal;
import game.entity.Witch;
import game.entity.Ghost;
import game.entity.NPC;
import game.items.FlowerTeleport;
import game.items.Walk;

public class Setting {
    GPanel gPanel;

    public Setting(GPanel gPanel) {
        this.gPanel = gPanel;
        setItems();
        setNPC();
        setAnimals();
    }


    private void setItems(){
        FlowerTeleport flowerTeleport = new FlowerTeleport(gPanel);
        flowerTeleport.setName("teleport");
        flowerTeleport.setItemX(gPanel.getTileSize()*21);
        flowerTeleport.setItemY(gPanel.getTileSize()*23);
        gPanel.getItems().add(flowerTeleport);

        FlowerTeleport flowerTeleport2 = new FlowerTeleport(gPanel);
        flowerTeleport2.setName("teleport");
        flowerTeleport2.setItemX(gPanel.getTileSize()*38);
        flowerTeleport2.setItemY(gPanel.getTileSize()*40);
        gPanel.getItems().add( flowerTeleport2);

        Walk walk = new Walk(gPanel);
        walk.setName("walk through");
        walk.setItemX(gPanel.getTileSize()*21);
        walk.setItemY(gPanel.getTileSize()*20);
        gPanel.getItems().add( walk);
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
        NPC npc1 = new Witch(gPanel);
        npc1.setX(gPanel.getTileSize()*21);
        npc1.setY(gPanel.getTileSize()*38);
        gPanel.getNpc().add(npc1);

        NPC npc2 = new Witch(gPanel);
        npc2.setX(gPanel.getTileSize()*20);
        npc2.setY(gPanel.getTileSize()*39);
        npc2.setName("haf");
        gPanel.getNpc().add(npc2);

        Ghost ghost = new Ghost(gPanel);
        ghost.setX(gPanel.getTileSize()*10);
        ghost.setY(gPanel.getTileSize()*10);
        gPanel.getNpc().add(ghost);
    }

    public void catchAnimal(){
        for (int i = 0; i < gPanel.getLostAnimals().size(); i++) {
            if (gPanel.getLostAnimals().get(i).entityArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getCaughtAnimals().add(gPanel.getLostAnimals().get(i));
                gPanel.getLostAnimals().remove(gPanel.getLostAnimals().get(i));
                gPanel.getGame().setGameState(gPanel.getGame().getCatchingAnimal());
            }
        }
    }

    private void setAnimals(){
        Animal mouse = new Animal(gPanel, "mouse", "mouse.jpg");
        mouse.setX(gPanel.getTileSize()*16);
        mouse.setY(gPanel.getTileSize()*20);
        gPanel.getLostAnimals().add(mouse);
    }


}
