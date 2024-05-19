package game.entity;

import game.GPanel;

import java.util.ArrayList;

public class OldLady extends NPC{
    public OldLady(GPanel gPanel) {
        super(gPanel);
        setName("oldLady");
        setDialogues("witch.txt");
        loadImage("cat.jpeg");
    }
}
