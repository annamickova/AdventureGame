package game;
import game.background.Tile;
import game.entity.Entity;

import java.awt.*;

public class CheckCollision {

    GPanel gPanel;

    public CheckCollision(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public boolean hasCollision(String direction, Entity entity) {
        int aX = entity.getX() + (gPanel.getTileSize()/2);
        int bX = entity.getX() + gPanel.getTileSize();
        int dX = entity.getX();

        int aY = entity.getY();
        int bY = entity.getY() + (gPanel.getTileSize()/2);
        int cY = entity.getY() + gPanel.getTileSize();

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

    public boolean hit(Entity e1, Entity e2){
        return e1.entityArea().intersects(e2.entityArea());
    }

    public boolean collisionWithOtherNPCs(int x, int y){
        Rectangle e = new Rectangle(x, y, gPanel.getTileSize(), gPanel.getTileSize());
        for (int i = 0; i < gPanel.getNpc().size(); i++) {

                if (e.intersects(gPanel.getNpc().get(i).entityArea())){
                    return true;
                }

        }
        return false;
    }

    public void npcMeetPlayer(){
        int n = 0;
        int j = 0;
        for (int i = 0; i < gPanel.getNpc().size(); i++) {
            if (gPanel.getPlayer().entityArea().intersects(gPanel.getNpc().get(i).entityAreaAround())){
                n++;
                j = i;
            }
        }
        if (n != 0){
            if (gPanel.getPlayer().isInteraction()){
                if (gPanel.getCurrDialogIndex() == gPanel.getNpc().get(j).getDialogIndex()){
                    gPanel.setGameState(gPanel.getDialog());
                    gPanel.getNpc().get(j).setText();
                }
            }
        }
    }
}
