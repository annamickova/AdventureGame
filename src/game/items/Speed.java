package game.items;

import game.GPanel;

public class Speed extends Item{

    public Speed(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/speed.png");
        setName("speed");
    }

    @Override
    public void function() {
        gPanel.getPlayer().setHighSpeed(true);
    }
}
