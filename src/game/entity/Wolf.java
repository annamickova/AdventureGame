package game.entity;

import game.GPanel;

public class Wolf extends NPC{
    public Wolf(GPanel gPanel) {
        super(gPanel);
        setName("wolf");
        setDialogues("cat.txt");
        loadImage("cat.jpeg");
    }
}
