package game;

import game.GPanel;
import game.background.Tile;
import game.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CollisionDetect {

    GPanel gPanel;

    public CollisionDetect(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public boolean hasCollision(String direction, int x, int y) {
        int aX = x + (gPanel.getTileSize()/2);
        int bX = x + gPanel.getTileSize();
        int dX = x;

        int aY = y;
        int bY = y + (gPanel.getTileSize()/2);
        int cY = y + gPanel.getTileSize();

        int tileX = 0;
        int tileY = 0;

        switch (direction){
            case "up" -> {
                tileX = aX;
                tileY = aY;
            }
            case "down" -> {
                tileX = aX;
                tileY = cY;
            }
            case "left" -> {
                tileX = dX;
                tileY = bY;
            }
            case "right" -> {
                tileX = bX;
                tileY = bY;
            }
        }

        tileX = tileX/ gPanel.getTileSize();
        tileY = tileY/ gPanel.getTileSize();

        Tile tile = gPanel.getbGround().getTile(tileX, tileY);

        return tile.isCollision();
    }
}
