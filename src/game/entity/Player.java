package game.entity;

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


        defValues();
        getImage();
    }

    private void defValues(){
        this.x = gPanel.getTileSize()*23;
        this.y = gPanel.getTileSize()*21;
        System.out.println(x+ " "+ y);

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

        if (keyHandler.moveUp || keyHandler.moveDown || keyHandler.moveLeft || keyHandler.moveRight) {
            if (keyHandler.moveUp) {
                newY -= speedP;
              direction = "up";
            } else if (keyHandler.moveDown) {
                direction = "down";
                newY += speedP;
            } else if (keyHandler.moveLeft) {
               direction = "left";
                newX -= speedP;
            } else if (keyHandler.moveRight) {
                direction = "right";
                newX += speedP;
            }
        }

        if (!hasCollision(newX, newY, direction)){
            x = newX;
            y = newY;
        }


    }

    private boolean hasCollision(int newX, int newY, String direction) {

        int tileX = newX / gPanel.getTileSize();
        int tileY = newY / gPanel.getTileSize();



        Tile tile = gPanel.getbGround().getTile(tileX, tileY);
        return tile.isCollision();
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(playerImage, getScreenX(), getScreenY(), gPanel.getTileSize(), gPanel.getTileSize(), null);
    }

    //   public boolean checkCollision(Rectangle objectBounds) {
     //   Rectangle playerBounds = new Rectangle(x, y, gPanel.getTileSize(), gPanel.getTileSize());
       // return playerBounds.intersects(objectBounds);
    //}

}
