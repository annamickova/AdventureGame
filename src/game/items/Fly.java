package game.items;

import game.GPanel;

public class Fly extends Item{

    public Fly(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/flower.jpg");
        setName("fly");
    }

    /**
     * Function that sets player's ability to fly.
     * He can move throught different tiles except wall.
     */
    @Override
    public void function() {
        gPanel.getPlayer().setFly(true);
        gPanel.getGame().setGameState(gPanel.getGame().getPlay());
    }


}
