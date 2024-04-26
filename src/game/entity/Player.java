package game.entity;

import game.CollisionDetect;
import game.GPanel;
import game.KeyHandler;
import game.background.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private GPanel gPanel;
    private KeyHandler keyHandler;
    private CollisionDetect collisionDetect;

    private int screenX;
    private int screenY;

    public int getScreenX() {
        return screenX  = (gPanel.getScreenWidth()/2) - (gPanel.getTileSize()/2);
    }

    public int getScreenY() {
        return screenY = (gPanel.getScreenHeight()/2) - (gPanel.getTileSize()/2);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }



    public Player(GPanel gPanel, KeyHandler keyHandler) {
        this.gPanel = gPanel;
        this.keyHandler = keyHandler;
        collisionDetect = new CollisionDetect(gPanel);

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

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

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

        if (!collisionDetect.hasCollision(direction,x,y)){
            x = newX;
            y = newY;
        }


    }



    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(playerImage, getScreenX(), getScreenY(), gPanel.getTileSize(), gPanel.getTileSize(), null);
    }

}
