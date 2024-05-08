package game.entity;

import game.GPanel;
import game.Game;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private KeyHandler keyHandler;

    private int screenX;
    private int screenY;

    public int getScreenX() {
        return screenX  = (gPanel.getScreenWidth()/2) - (gPanel.getTileSize()/2);
    }

    public int getScreenY() {
        return screenY = (gPanel.getScreenHeight()/2) - (gPanel.getTileSize()/2);
    }

    public Player(GPanel gPanel, KeyHandler keyHandler) {
        super(gPanel);
        this.keyHandler = keyHandler;

        defValues();
        getImage();
    }

    private void defValues(){
        this.x = gPanel.getTileSize()*23;
        this.y = gPanel.getTileSize()*21;

        speedP = 4;
        direction = "down";
    }

    private void getImage() {
        try {
            playerImage = ImageIO.read(new File("cat.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        int num = 0;

        if (!collisionDetect.hasCollision(direction, this)){
            for (int i = 0; i < gPanel.getNpc().size(); i++) {
                if (entityNewArea(newX, newY).intersects(gPanel.getNpc().get(i).entityArea())){
                    num++;
                }
            }
            if (num == 0){
                x = newX;
                y = newY;
            }
            collisionDetect.npcMeetPlayer();

        }
       gPanel.getSettings().collectItem();
    }



    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(playerImage, getScreenX(), getScreenY(),
                gPanel.getTileSize(), gPanel.getTileSize(), null);
    }

}
