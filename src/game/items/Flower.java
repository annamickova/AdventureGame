package game.items;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Flower extends Item{

    public Flower() {
        name = "flower";
        try {
            itemImage = ImageIO.read(new File("flower2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
