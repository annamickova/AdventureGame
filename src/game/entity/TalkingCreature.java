package game.entity;

import game.GPanel;

public class TalkingCreature extends NPC{

    public TalkingCreature(GPanel gPanel, String fileName, String dialog) {
        super(gPanel);
        setName("creature");
        setDialogues(dialog);
        loadImage(fileName);
    }

    /**
     * Creature`s movement.
     */
    @Override
    public void move() {
        super.move();
    }
}
