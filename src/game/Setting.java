package game;

import game.entity.*;
import game.items.*;

import java.awt.*;
import java.util.Random;

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
     * Generating random coordination for items.
     * @return
     */
    public Point generateCoordinates() {
        Random rd = new Random();
        int x;
        int y;
        do {
            x = rd.nextInt(60);
            y = rd.nextInt(60);
        } while (gPanel.getbGround().getTile(x, y).isCollision());
        return new Point(x, y);
    }
    /**
     * Creating new items and placing them into map.
     */
    private void setFunctionItems(){
        Point cor;
        Teleport teleport = new Teleport(gPanel);
        teleport.setItemX(gPanel.getTileSize()*21);
        teleport.setItemY(gPanel.getTileSize()*7);
        teleport.setVisible(false);
        gPanel.getFunctionItems().add(teleport);

        Teleport teleport2 = new Teleport(gPanel);
        cor = generateCoordinates();
        teleport2.setItemX(gPanel.getTileSize()*cor.x);
        teleport2.setItemY(gPanel.getTileSize()*cor.y);
        teleport2.setVisible(true);
        gPanel.getFunctionItems().add(teleport2);

        Fly fly = new Fly(gPanel);
        fly.setItemX(gPanel.getTileSize()*52);
        fly.setItemY(gPanel.getTileSize()*3);
        fly.setVisible(true);
        gPanel.getFunctionItems().add(fly);

        Fly fly2 = new Fly(gPanel);
        cor = generateCoordinates();
        fly2.setItemX(gPanel.getTileSize()*cor.x);
        fly2.setItemY(gPanel.getTileSize()*cor.y);
        fly2.setVisible(true);
        gPanel.getFunctionItems().add(fly2);

        Speed speed = new Speed(gPanel);
        cor = generateCoordinates();
        speed.setItemX(gPanel.getTileSize()*cor.x);
        speed.setItemY(gPanel.getTileSize()*cor.y);
        speed.setVisible(true);
        gPanel.getFunctionItems().add(speed);

        Swim swim = new Swim(gPanel);
        cor = generateCoordinates();
        swim.setItemX(gPanel.getTileSize()*cor.x);
        swim.setItemY(gPanel.getTileSize()*cor.y);
        swim.setVisible(true);
        gPanel.getFunctionItems().add(swim);
    }

    /**
     * Creating and placing fuel tanks.
     */
    private void setFuel(){
        Point cor;
        Fuel f1 = new Fuel(gPanel);
        f1.setItemX(gPanel.getTileSize()*4);
        f1.setItemY(gPanel.getTileSize()*23);
        gPanel.getLostItems().add(f1);

        Fuel f2 = new Fuel(gPanel);
        f2.setItemX(gPanel.getTileSize()*52);
        f2.setItemY(gPanel.getTileSize()*53);
        gPanel.getLostItems().add(f2);

        Fuel f3 = new Fuel(gPanel);
        cor = generateCoordinates();
        f3.setItemX(gPanel.getTileSize()*cor.x);
        f3.setItemY(gPanel.getTileSize()*cor.y);
        gPanel.getLostItems().add(f3);

        Fuel f4 = new Fuel(gPanel);
        cor = generateCoordinates();
        f4.setItemX(gPanel.getTileSize()*cor.x);
        f4.setItemY(gPanel.getTileSize()*cor.y);
        gPanel.getLostItems().add(f4);

        Fuel f5 = new Fuel(gPanel);
        cor = generateCoordinates();
        f5.setItemX(gPanel.getTileSize()*cor.x);
        f5.setItemY(gPanel.getTileSize()*cor.y);
        gPanel.getLostItems().add(f5);

        Fuel f6 = new Fuel(gPanel);
        f6.setItemX(gPanel.getTileSize()*15);
        f6.setItemY(gPanel.getTileSize()*22);
        gPanel.getLostItems().add(f6);

        Fuel f7 = new Fuel(gPanel);
        cor = generateCoordinates();
        f7.setItemX(gPanel.getTileSize()*cor.x);
        f7.setItemY(gPanel.getTileSize()*cor.y);
        gPanel.getLostItems().add(f7);

        Fuel f8 = new Fuel(gPanel);
        cor = generateCoordinates();
        f8.setItemX(gPanel.getTileSize()*cor.x);
        f8.setItemY(gPanel.getTileSize()*cor.y);
        gPanel.getLostItems().add(f8);
    }

    /**
     * Creating new npc character with dialog, setting their location and adding into list.
     * @throws Exception
     */
    private void setNPC() throws Exception{
        TalkingCreature npc1 = new TalkingCreature(gPanel,"assets/animal.png", "assets/creature.txt");
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
        Point cor;
        Creature creature1 = new Creature(gPanel, "creature1", "assets/creature.png");
        creature1.setX(gPanel.getTileSize()*16);
        creature1.setY(gPanel.getTileSize()*20);
        gPanel.getCreatures().add(creature1);

        Creature creature2 = new Creature(gPanel, "butterfly2", "assets/butterfly.png");
        cor = generateCoordinates();
        creature2.setX(gPanel.getTileSize()*cor.x);
        creature2.setY(gPanel.getTileSize()*cor.y);
        gPanel.getCreatures().add(creature2);

        Creature creature3 = new Creature(gPanel, "creature3","assets/creature.png");
        cor = generateCoordinates();
        creature3.setX(gPanel.getTileSize()*cor.x);
        creature3.setY(gPanel.getTileSize()*cor.y);
        gPanel.getCreatures().add(creature3);

        Creature creature4 = new Creature(gPanel, "butterfly4", "assets/butterfly.png");
        cor = generateCoordinates();
        creature4.setX(gPanel.getTileSize()*cor.x);
        creature4.setY(gPanel.getTileSize()*cor.y);
        gPanel.getCreatures().add(creature4);

        Creature creature5 = new Creature(gPanel, "butterfly5", "assets/butterfly.png");
        cor = generateCoordinates();
        creature5.setX(gPanel.getTileSize()*cor.x);
        creature5.setY(gPanel.getTileSize()*cor.y);
        gPanel.getCreatures().add(creature5);

        Creature creature6 = new Creature(gPanel, "creature6","assets/creature.png");
        cor = generateCoordinates();
        creature6.setX(gPanel.getTileSize()*cor.x);
        creature6.setY(gPanel.getTileSize()*cor.y);
        gPanel.getCreatures().add(creature6);
    }
}
