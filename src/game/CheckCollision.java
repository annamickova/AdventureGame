package game;
import game.background.Tile;
import game.entity.Entity;
import game.entity.NPC;

import java.awt.*;

public class CheckCollision {

    private GPanel gPanel;

    public CheckCollision(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    /**
     * Detecting collision with entity and tiles according to their direction.
     * @param entity
     * @return
     */
    public boolean hasCollision(Entity entity) {
        int aX = entity.getX() + (gPanel.getTileSize()/2);
        int bX = entity.getX() + gPanel.getTileSize();
        int dX = entity.getX();

        int aY = entity.getY();
        int bY = entity.getY() + (gPanel.getTileSize()/2);
        int cY = entity.getY() + gPanel.getTileSize();

        int tileX = 0;
        int tileY = 0;

        switch (entity.getDirection()){
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

    /**
     * Collision betweeen two entities.
     * @param e1
     * @param e2
     * @return
     */
    public boolean hit(Entity e1, Entity e2){
        return e1.entityArea().intersects(e2.entityArea());
    }

    public boolean collisionWithOtherNPCs(NPC npc){
        boolean c = false;
        for (int i = 0; i < gPanel.getNpc().size(); i++) {
            if (npc.entityArea().intersects(gPanel.getNpc().get(i).entityArea())){
                if (!gPanel.getNpc().get(i).equals(npc)){
                    c = true;
                }
            }

        }
        return c;
    }

    /**
     * Setting npc dialog when he meets player, calling method to increase dialog index when all npc reached the same.
     */
    public void npcMeetPlayer(){
        boolean n = false;
        int j = 0;
        for (int i = 0; i < gPanel.getNpc().size(); i++) {
            if (gPanel.getPlayer().entityArea().intersects(gPanel.getNpc().get(i).entityAreaAround())){
                n = true;
                j = i;
            }
        }
        if (n){
            if (gPanel.getPlayer().isInteraction()){
                if (gPanel.getCurrDialogIndex() == gPanel.getNpc().get(j).getDialogIndex()){
                    gPanel.getGame().setGameState(gPanel.getGame().getDialog());
                    gPanel.getNpc().get(j).setText();
                }
            }
        }
    }

    /**
     * Checking collision on all tiles except one.
     * @param entity
     * @param tileName
     * @return
     */
    public boolean collisionWithout(Entity entity, String tileName){
        int aX = entity.getX() + (gPanel.getTileSize()/2);
        int bX = entity.getX() + gPanel.getTileSize();
        int dX = entity.getX();

        int aY = entity.getY();
        int bY = entity.getY() + (gPanel.getTileSize()/2);
        int cY = entity.getY() + gPanel.getTileSize();

        int tileX = 0;
        int tileY = 0;

        switch (entity.getDirection()){
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

        String tile = gPanel.getbGround().getTile(tileX, tileY).getName();

        return tile.equals(tileName);
    }

    /**
     * Checking colliion between player and npc.
     * @param newX
     * @param newY
     */
    public void coll(int newX, int newY){
        boolean c = false;
        if (!hasCollision(gPanel.getPlayer())){
            for (int i = 0; i < gPanel.getNpc().size(); i++) {
                if (gPanel.getPlayer().entityNewArea(newX, newY).intersects(gPanel.getNpc().get(i).entityArea())){
                    c = true;
                }
            }
            if (!c){
                try {
                    gPanel.getPlayer().setX(newX);
                    gPanel.getPlayer().setY(newY);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Moving player when he meets guard.
     * @param entity
     */
    public void throwPlayer(Entity entity){
        if (entity.entityArea().intersects(gPanel.getPlayer().entityArea())){
            try {
                gPanel.getPlayer().setX(gPanel.getTileSize());
                gPanel.getPlayer().setY(58*gPanel.getTileSize());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
