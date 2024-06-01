package game;

import game.entity.*;
import game.items.*;

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
        teleport.setItemY(gPanel.getTileSize()*7);
        teleport.setVisible(false);
        gPanel.getFunctionItems().add(teleport);

        Teleport teleport2 = new Teleport(gPanel);
        teleport2.setItemX(gPanel.getTileSize()*38);
        teleport2.setItemY(gPanel.getTileSize()*40);
        teleport2.setVisible(true);
        gPanel.getFunctionItems().add(teleport2);

        Fly fly = new Fly(gPanel);
        fly.setItemX(gPanel.getTileSize()*21);
        fly.setItemY(gPanel.getTileSize()*20);
        fly.setVisible(true);
        gPanel.getFunctionItems().add(fly);

        Speed speed = new Speed(gPanel);
        speed.setItemX(gPanel.getTileSize()*23);
        speed.setItemY(gPanel.getTileSize()*40);
        speed.setVisible(true);
        gPanel.getFunctionItems().add(speed);

        Swim swim = new Swim(gPanel);
        swim.setItemX(gPanel.getTileSize()*21);
        swim.setItemY(gPanel.getTileSize()*21);
        swim.setVisible(true);
        gPanel.getFunctionItems().add(swim);

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

        Fuel f7 = new Fuel(gPanel);
        f7.setItemX(gPanel.getTileSize()*30);
        f7.setItemY(gPanel.getTileSize()*29);
        gPanel.getLostItems().add(f7);

        Fuel f8 = new Fuel(gPanel);
        f8.setItemX(gPanel.getTileSize()*37);
        f8.setItemY(gPanel.getTileSize()*40);
        gPanel.getLostItems().add(f8);
    }

    /**
     * Creating new npc character with dialog, setting their location and adding into list.
     * @throws Exception
     */
    private void setNPC() throws Exception{
        TalkingCreature npc1 = new TalkingCreature(gPanel,"assets/animal.png", "assets/littleDeer.txt");
        npc1.setX(gPanel.getTileSize()*45);
        npc1.setY(gPanel.getTileSize()*51);
        gPanel.getNpc().add(npc1);

        TalkingCreature npc2 = new TalkingCreature(gPanel, "assets/animal.png", "assets/npc.txt");
        npc2.setX(gPanel.getTileSize()*20);
        npc2.setY(gPanel.getTileSize()*39);
        gPanel.getNpc().add(npc2);

        Ghost ghost = new Ghost(gPanel);
        ghost.setX(gPanel.getTileSize()*10);
        ghost.setY(gPanel.getTileSize()*10);
        gPanel.getNpc().add(ghost);

    }

    /**
     * Creating new creatures and adding them into lost animals array list.
     * @throws Exception
     */
    private void setCreatures() throws Exception{
        Creature creature1 = new Creature(gPanel, "creature", "assets/creature.png");
        creature1.setX(gPanel.getTileSize()*16);
        creature1.setY(gPanel.getTileSize()*20);
        gPanel.getCreatures().add(creature1);

        Creature creature2 = new Creature(gPanel, "butterfly", "assets/butterfly.png");
        creature2.setX(gPanel.getTileSize()*16);
        creature2.setY(gPanel.getTileSize()*21);
        gPanel.getCreatures().add(creature2);

       /* Creature creature3 = new Creature(gPanel, "creature3", "assets/spider.png");
        creature3.setX(gPanel.getTileSize()*22);
        creature3.setY(gPanel.getTileSize()*35);
        gPanel.getCreatures().add(creature3);

        Creature creature4 = new Creature(gPanel, "creature4", "assets/spider.png");
        creature4.setX(gPanel.getTileSize()*39);
        creature4.setY(gPanel.getTileSize()*10);
        gPanel.getCreatures().add(creature4); */
    }


}
