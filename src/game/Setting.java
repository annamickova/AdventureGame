package game;

import game.entity.*;
import game.items.Item;
import game.items.Teleport;
import game.items.Walk;

import javax.imageio.ImageIO;
import java.io.File;

public class Setting {
   private GPanel gPanel;

    public Setting(GPanel gPanel) throws Exception{
        this.gPanel = gPanel;
        setFunctionItems();
        setItems();
        setNPC();
        setCreatures();
    }

    /**
     * Creating new items and placing them into map.
     */
    private void setFunctionItems(){
        Teleport teleport = new Teleport(gPanel);
        teleport.setName("teleport");
        teleport.setItemX(gPanel.getTileSize()*21);
        teleport.setItemY(gPanel.getTileSize()*23);
        gPanel.getFunctionItems().add(teleport);

        Teleport teleport2 = new Teleport(gPanel);
        teleport2.setName("teleport");
        teleport2.setItemX(gPanel.getTileSize()*38);
        teleport2.setItemY(gPanel.getTileSize()*40);
        gPanel.getFunctionItems().add(teleport2);

        Walk walk = new Walk(gPanel);
        walk.setName("walk through");
        walk.setItemX(gPanel.getTileSize()*21);
        walk.setItemY(gPanel.getTileSize()*20);
        gPanel.getFunctionItems().add( walk);

    }

    private void setItems(){
        Teleport item = new Teleport(gPanel);
        item.setName("teleport");
        item.setItemX(gPanel.getTileSize()*24);
        item.setItemY(gPanel.getTileSize()*23);
        gPanel.getLostItems().add(item);

        Teleport item2 = new Teleport(gPanel);
        item2.setName("teleport");
        item2.setItemX(gPanel.getTileSize()*24);
        item2.setItemY(gPanel.getTileSize()*20);
        gPanel.getLostItems().add(item2);
    }

    /**
     * Moving item into already collected items.
     */
    public void collectItem(){
        for (int i = 0; i < gPanel.getFunctionItems().size(); i++) {
            if (gPanel.getFunctionItems().get(i).itemArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getCollectedFunctionItems().add(0, gPanel.getFunctionItems().get(i));
                gPanel.getFunctionItems().remove(gPanel.getFunctionItems().get(i));
            }
        }
    }

    /**
     * Creating new npc, setting their location and adding into list.
     * @throws Exception
     */
    private void setNPC() throws Exception{
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

    /**
     * Method removes animal from lost and adds the anima into caught array.
     */
  /*  public void catchCreature(){
        for (int i = 0; i < gPanel.getLostAnimals().size(); i++) {
            if (gPanel.getLostAnimals().get(i).entityArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getCaughtAnimals().add(gPanel.getLostAnimals().get(i));
                gPanel.getLostAnimals().remove(gPanel.getLostAnimals().get(i));
            }
        }
    }*/

    public void takeItem(){
        for (int i = 0; i < gPanel.getLostItems().size(); i++) {
            if (gPanel.getLostItems().get(i).itemArea().intersects(gPanel.getPlayer().entityArea())){
                gPanel.getCollectedItems().add(gPanel.getLostItems().get(i));
                gPanel.getLostItems().remove(gPanel.getLostItems().get(i));
            }
        }
    }

    /**
     * Creating new creatures and adding them into lost animals array list.
     * @throws Exception
     */
    private void setCreatures() throws Exception{
        Creature mouse = new Creature(gPanel, "mouse", "mouse.jpg");
        mouse.setX(gPanel.getTileSize()*16);
        mouse.setY(gPanel.getTileSize()*20);
        gPanel.getLostAnimals().add(mouse);

        Creature butterfly = new Creature(gPanel, "butterfly", "butterfly.jpg");
        butterfly.setX(gPanel.getTileSize()*16);
        butterfly.setY(gPanel.getTileSize()*21);
        gPanel.getLostAnimals().add(butterfly);

        Creature butterfly2 = new Creature(gPanel, "butterfly2", "butterfly.jpg");
        butterfly2.setX(gPanel.getTileSize()*22);
        butterfly2.setY(gPanel.getTileSize()*35);
        gPanel.getLostAnimals().add(butterfly2);
    }


}
