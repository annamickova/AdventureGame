package game.entity;

import game.GPanel;

public class Creature extends NPC{
    public Creature(GPanel gPanel, String name, String image) {
        super(gPanel);
        setName(name);
        loadImage(image);
    }

    @Override
    public void move() {
        super.move();
    }



}
