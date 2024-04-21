package game.entity;

import game.GPanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private GPanel gPanel;
    private KeyHandler keyHandler;

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

        x = gPanel.getTileSize() * 23;
        y = gPanel.getTileSize() * 21;
        speedP = 4;
        direction = "down";

        getImage();
    }

    public void getImage() {
        try {
            playerImage = ImageIO.read(new File("cat.jpeg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public void update() {

        if (keyHandler.moveUp || keyHandler.moveDown || keyHandler.moveLeft || keyHandler.moveRight) {
            if (keyHandler.moveUp) {
                y -= speedP;
              direction = "up";
            } else if (keyHandler.moveDown) {
                direction = "down";
                y += speedP;
            } else if (keyHandler.moveLeft) {
               direction = "left";
                x -= speedP;
            } else if (keyHandler.moveRight) {
                direction = "right";
                x += speedP;
            }
        }

        /*switch (direction){
            case "up" -> y -= speedP;
            case "down" -> y += speedP;
            case "left" -> x -= speedP;
            case "right" ->  x += speedP;
        }*/

    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(playerImage, getScreenX(), getScreenY(), gPanel.getTileSize(), gPanel.getTileSize(), null);
    }

    //   public boolean checkCollision(Rectangle objectBounds) {
     //   Rectangle playerBounds = new Rectangle(x, y, gPanel.getTileSize(), gPanel.getTileSize());
       // return playerBounds.intersects(objectBounds);
    //}

}
