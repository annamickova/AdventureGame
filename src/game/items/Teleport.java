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

    @Override
    public void action() {
        gPanel.getGame().teleport();
    }
}
