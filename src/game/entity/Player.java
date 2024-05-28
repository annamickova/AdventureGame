package game.entity;

import game.GPanel;
import game.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    private KeyHandler keyHandler;

    private int screenX;
    private int screenY;
    private boolean walkThrough;

    public Player(GPanel gPanel, KeyHandler keyHandler) {
        super(gPanel);
        this.keyHandler = keyHandler;

        defValues();
        loadImage("cat.jpeg");
    }

    private void defValues(){
        this.x = gPanel.getTileSize()*23;
        this.y = gPanel.getTileSize()*21;
        walkThrough = false;
        speedP = 4;
        direction = "down";
    }

    public int getScreenX() {
        return screenX  = (gPanel.getScreenWidth()/2) - (gPanel.getTileSize()/2);
    }

    public int getScreenY() {
        return screenY = (gPanel.getScreenHeight()/2) - (gPanel.getTileSize()/2);
    }

    public boolean isWalkThrough() {
        return walkThrough;
    }

    public void setWalkThrough(boolean walkThrough) {
        this.walkThrough = walkThrough;
    }

    /**
     * Moves if there is no collision, calls method to catch item or creature.
     */
    @Override
    public void update() {

        int newX = x;
        int newY = y;

        if (keyHandler.moveUp) {
            newY -= speedP;
            direction = "up";
        }
        if (keyHandler.moveDown ) {
            newY += speedP;
            direction = "down";
        }
        if (keyHandler.moveLeft) {
            newX -= speedP;
            direction = "left";
        }
        if (keyHandler.moveRight) {
            newX += speedP;
            direction = "right";
        }
       if (!walkThrough) {
          getCheckCollision().coll(newX, newY);
       } if (walkThrough){
           if (!getCheckCollision().collisionWithout(this, "wall")){
               x = newX;
               y = newY;
           }
       }
       checkCollision.npcMeetPlayer();
       gPanel.getSettings().collectItem();
       gPanel.getSettings().catchCreature();
    }


    /**
     * Drawing player on screen.
     * @param graphics2D
     */
    public void drawPlayer(Graphics2D graphics2D) {
        graphics2D.drawImage(entityImage, getScreenX(), getScreenY(),
                gPanel.getTileSize(), gPanel.getTileSize(), null);
    }

}
