package game.entity;

import game.GPanel;

import java.util.Random;

public class Cat extends NPC{

    public Cat(GPanel gPanel) {
        super(gPanel);
        setName("cat");
        setDialogues("cat.txt");
        loadImage("cat.jpeg");
    }

    @Override
    public void move() {
        super.move();
    }
}
