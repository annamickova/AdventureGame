package game.entity;

import game.GPanel;

public class Animal extends NPC{

    public Animal(GPanel gPanel, String name, String image) {
        super(gPanel);
        setName(name);
        loadImage(image);
    }

    @Override
    public void move() {
        super.move();
    }



}
