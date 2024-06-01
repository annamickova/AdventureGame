package game.items;

import game.GPanel;

public class Swim extends Item{
    public Swim(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/raindrop.png");
        setName("swim");
    }

    /**
     * Setting swimming ability so player can move through water.
     */
    @Override
    public void function() {
        gPanel.getPlayer().setSwimming(true);
    }
}
