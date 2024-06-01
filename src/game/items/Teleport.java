package game.items;

import game.GPanel;
import game.screenStates.GameState;

public class Teleport extends Item{

    public Teleport(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/flower1.png");
        setName("teleport");
    }

    /**
     *  Function for teleporting. Moves player on starting location.
     */
    @Override
    public void function() {
        try {
            gPanel.getPlayer().setX(gPanel.getTileSize() * 23);
            gPanel.getPlayer().setY(gPanel.getTileSize() * 21);
            gPanel.getGame().setGameState(GameState.PLAY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
