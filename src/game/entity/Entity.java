package game.entity;

import game.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    protected GPanel gPanel;
    protected int x;
    protected int y;
    protected int speedP;

    protected BufferedImage playerImage;
    protected String direction;

    public Entity(GPanel gPanel) {
        this.gPanel = gPanel;
    }
}
