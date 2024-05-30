package game;

import game.entity.*;
import game.items.Fuel;
import game.items.Speed;
import game.items.Teleport;
import game.items.Walk;

public class Setting {
   private GPanel gPanel;

    public Setting(GPanel gPanel) throws Exception{
        this.gPanel = gPanel;
        setFunctionItems();
        setFuel();
        setNPC();
        setCreatures();
    }

    /**
     * Creating new items and placing them into map.
     */
    private void setFunctionItems(){
        Teleport teleport = new Teleport(gPanel);
        teleport.setItemX(gPanel.getTileSize()*21);
        teleport.setItemY(gPanel.getTileSize()*23);
        gPanel.getFunctionItems().add(teleport);

        Teleport teleport2 = new Teleport(gPanel);
        teleport2.setItemX(gPanel.getTileSize()*38);
        teleport2.setItemY(gPanel.getTileSize()*40);
        gPanel.getFunctionItems().add(teleport2);

        Walk walk = new Walk(gPanel);
        walk.setItemX(gPanel.getTileSize()*21);
        walk.setItemY(gPanel.getTileSize()*20);
        gPanel.getFunctionItems().add(walk);

        Speed speed = new Speed(gPanel);
        speed.setItemX(gPanel.getTileSize()*23);
        speed.setItemY(gPanel.getTileSize()*40);
        gPanel.getFunctionItems().add(speed);

    }

    private void setFuel(){
        Fuel f1 = new Fuel(gPanel);
        f1.setItemX(gPanel.getTileSize()*4);
        f1.setItemY(gPanel.getTileSize()*23);
        gPanel.getLostItems().add(f1);

        Fuel f2 = new Fuel(gPanel);
        f2.setItemX(gPanel.getTileSize()*52);
        f2.setItemY(gPanel.getTileSize()*53);
        gPanel.getLostItems().add(f2);

        Fuel f3 = new Fuel(gPanel);
        f3.setItemX(gPanel.getTileSize()*21);
        f3.setItemY(gPanel.getTileSize()*39);
        gPanel.getLostItems().add(f3);

        Fuel f4 = new Fuel(gPanel);
        f4.setItemX(gPanel.getTileSize()*11);
        f4.setItemY(gPanel.getTileSize()*25);
        gPanel.getLostItems().add(f4);

        Fuel f5 = new Fuel(gPanel);
        f5.setItemX(gPanel.getTileSize()*57);
        f5.setItemY(gPanel.getTileSize()*3);
        gPanel.getLostItems().add(f5);

        Fuel f6 = new Fuel(gPanel);
        f6.setItemX(gPanel.getTileSize()*15);
        f6.setItemY(gPanel.getTileSize()*22);
        gPanel.getLostItems().add(f6);
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
        NPC npc1 = new TalkingCreature(gPanel,"creature1.png", "witch.txt");
        npc1.setX(gPanel.getTileSize()*45);
        npc1.setY(gPanel.getTileSize()*51);
        gPanel.getNpc().add(npc1);

        NPC npc2 = new TalkingCreature(gPanel, "creature2.png", "witch.txt");
        npc2.setX(gPanel.getTileSize()*20);
        npc2.setY(gPanel.getTileSize()*39);
        gPanel.getNpc().add(npc2);

        Ghost ghost = new Ghost(gPanel);
        ghost.setX(gPanel.getTileSize()*10);
        ghost.setY(gPanel.getTileSize()*10);
        gPanel.getNpc().add(ghost);

    }

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
        gPanel.getCreatures().add(mouse);

        Creature butterfly = new Creature(gPanel, "butterfly", "butterfly.jpg");
        butterfly.setX(gPanel.getTileSize()*16);
        butterfly.setY(gPanel.getTileSize()*21);
        gPanel.getCreatures().add(butterfly);

        Creature butterfly2 = new Creature(gPanel, "butterfly2", "littleDeer.png");
        butterfly2.setX(gPanel.getTileSize()*22);
        butterfly2.setY(gPanel.getTileSize()*35);
        gPanel.getCreatures().add(butterfly2);
    }


}
