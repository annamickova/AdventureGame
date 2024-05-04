package game;
import game.background.Tile;
import game.entity.Entity;

public class CollisionDetect {

    GPanel gPanel;

    public CollisionDetect(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public boolean hasCollision(String direction, int x, int y , Entity entity) {
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
}
