package game.items;

import game.GPanel;

public class Swim extends Item{
    public Swim(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/raindrop.png");
        setName("swim");
    }

    @Override
    public void function() {
        gPanel.getPlayer().setSwimmming(true);
    }
}
