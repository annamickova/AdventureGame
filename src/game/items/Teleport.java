package game.items;

import game.GPanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Teleport extends Item{

    public Teleport(GPanel gPanel) {
        super(gPanel);
        try {
            itemImage = ImageIO.read(new File("flower2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Teleport function. Moves player on starting location.
     */
    @Override
    public void function() {
        try {
            gPanel.getPlayer().setX(gPanel.getTileSize() * 23);
            gPanel.getPlayer().setY(gPanel.getTileSize() * 21);
            gPanel.getGame().setGameState(gPanel.getGame().getPlay());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
