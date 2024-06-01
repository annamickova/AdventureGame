package game.entity;

import game.GPanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    private KeyHandler keyHandler;

    private int screenX;
    private int screenY;
    private boolean fly;
    private boolean swimmming;
    private int lives;
    private BufferedImage livesImage;
    private boolean highSpeed;

    public boolean isSwimmming() {
        return swimmming;
    }

    public void setSwimmming(boolean swimmming) {
        this.swimmming = swimmming;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        if (lives > 0){
            this.lives = lives;
        }else {
            this.lives = 0;
        }

    }
    public int getScreenX() {
        return screenX  = (gPanel.getScreenWidth()/2) - (gPanel.getTileSize()/2);
    }

    public int getScreenY() {
        return screenY = (gPanel.getScreenHeight()/2) - (gPanel.getTileSize()/2);
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public boolean isHighSpeed() {
        return highSpeed;
    }

    public void setHighSpeed(boolean highSpeed) {
        this.highSpeed = highSpeed;
    }
    public BufferedImage getLivesImage() {
        return livesImage;
    }

    public Player(GPanel gPanel, KeyHandler keyHandler) {
        super(gPanel);
        this.keyHandler = keyHandler;
        defValues();
        loadImage("assets/cat.jpeg");
        loadLivesImage("assets/heart2.png");
    }

    /**
     * Setting player's default values.
     */
    private void defValues(){
        this.x = gPanel.getTileSize()*23;
        this.y = gPanel.getTileSize()*21;
        fly = false;
        speed = 4;
        direction = "down";
        lives = 3;
    }

    /**
     * Setting heart image.
     * @param fileName
     */
    private void loadLivesImage(String fileName) {
        try {
            livesImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves if there is no collision, calls method to collect item.
     */
    @Override
    public void update() {
        int newX = x;
        int newY = y;
        if (keyHandler.moveUp) {
            newY -= speed;
            direction = "up";
        }
        if (keyHandler.moveDown ) {
            newY += speed;
            direction = "down";
        }
        if (keyHandler.moveLeft) {
            newX -= speed;
            direction = "left";
        }
        if (keyHandler.moveRight) {
            newX += speed;
            direction = "right";
        }
       if (fly){
           if (!getCheckCollision().collisionWithout(this, "wall")){
               x = newX;
               y = newY;
           }
       }else if (swimmming){
            if (getCheckCollision().collisionWithout(this, "water")){
                x = newX;
                y = newY;
            } else if (getCheckCollision().collisionWithout(this, "grass")) {
                x = newX;
                y = newY;
            } else if (getCheckCollision().collisionWithout(this, "sand")) {
                x = newX;
                y = newY;
            }
       }else{
           getCheckCollision().coll(newX, newY);
       }
       checkCollision.npcMeetPlayer();
       checkCollision.collectItem();
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
