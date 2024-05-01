package game.entity;

import game.GPanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity {
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speedP = 1;
        getImage();
    }

    private void getImage() {
        try {
            playerImage = ImageIO.read(new File("cat.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void action() {
        Random rd = new Random();
        int number = rd.nextInt(4);
        switch (number){
            case 0 -> direction = "up";
            case 1 -> direction = "down";
            case 2 -> direction = "left";
            case 3 -> direction = "right";
        }
    }
}
