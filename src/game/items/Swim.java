package game.items;

import game.GPanel;

public class Swim extends Item{
    public Swim(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/flower.jpg");
        setName("boat");
    }

    @Override
    public void function() {
        gPanel.getPlayer().setSwimmming(true);
    }
}
