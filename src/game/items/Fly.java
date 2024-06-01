package game.items;

import game.GPanel;
import game.screenStates.GameState;

public class Fly extends Item{

    public Fly(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/cloud.png");
        setName("fly");
    }

    /**
     * Function that sets player's ability to fly.
     * He can move through different tiles except wall.
     */
    @Override
    public void function() {
        gPanel.getPlayer().setFly(true);
        gPanel.getGame().setGameState(GameState.PLAY);
    }


}
