package game.entity;

import game.GPanel;

public class Cat extends NPC{

    public Cat(GPanel gPanel) {
        super(gPanel);

        setName("cici");
        setDialogues("npc.txt");
        loadImage("cat.jpeg");
    }
}
