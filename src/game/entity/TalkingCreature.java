package game.entity;

import game.GPanel;

public class TalkingCreature extends NPC{

    public TalkingCreature(GPanel gPanel, String fileName, String dialog) {
        super(gPanel);
        setName("witch");
        setDialogues(dialog);
        loadImage(fileName);
    }

    @Override
    public void move() {
        super.move();
    }
}
