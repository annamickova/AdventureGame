package game.items;

import game.GPanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Walk extends Item{

    public Walk(GPanel gPanel) {
        super(gPanel);
        try {
            itemImage = ImageIO.read(new File("flower.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function that sets player's ability to walk through different tiles.
     */
    @Override
    public void function() {
        gPanel.getPlayer().setWalkThrough(true);
        gPanel.getGame().setGameState(gPanel.getGame().getPlay());
    }


}
