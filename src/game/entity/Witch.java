package game.entity;

import game.GPanel;

import java.util.ArrayList;

public class Witch extends NPC{

    public Witch(GPanel gPanel) {
        super(gPanel);
        setName("witch");
        setDialogues("witch.txt");
        loadImage("cat.jpeg");
    }

    @Override
    public void move() {
        super.move();
    }
}
