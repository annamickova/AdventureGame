package game.entity;

import game.GPanel;
import game.Game;

public class Cat extends NPC{

    public Cat(GPanel gPanel) {
        super(gPanel);

        setName("cici");
        setDialogues("cat.txt");
        loadImage("cat.jpeg");
    }
}
