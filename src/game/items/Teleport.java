package game.items;

import game.GPanel;
import game.screenStates.GameState;

import java.awt.*;

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
        Point cor;
        cor = gPanel.getSetting().generateCoordinates();
        try {
            gPanel.getPlayer().setX(gPanel.getTileSize() * cor.x);
            gPanel.getPlayer().setY(gPanel.getTileSize() * cor.y);
            gPanel.getGame().setGameState(GameState.PLAY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
