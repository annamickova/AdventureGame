package game.entity;

import game.CheckCollision;
import game.GPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity {

    protected GPanel gPanel;
    protected int x;
    protected int y;
    protected int speedP;
    protected CheckCollision checkCollision;

    protected BufferedImage playerImage;
    protected String direction;
    protected boolean interaction;

    public Entity(GPanel gPanel) {
        this.gPanel = gPanel;
        this.checkCollision = new CheckCollision(gPanel);

    }

    public String getDirection() {
        return direction;
    }

    public CheckCollision getCheckCollision() {
        return checkCollision;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInteraction() {
        return interaction;
    }

    public void setInteraction(boolean interaction) {
        this.interaction = interaction;
    }

    public void drawEntity(Graphics2D graphics2D){
        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY()+ gPanel.getPlayer().getScreenY();

        if (x + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY()){
            graphics2D.drawImage(playerImage, screenX, screenY, gPanel.getTileSize(), gPanel.getTileSize(), null);

        }
    }

    public Rectangle entityAreaAround(){
        return new Rectangle(x-gPanel.getTileSize(), y-gPanel.getTileSize(),
                gPanel.getTileSize()*3, gPanel.getTileSize()*3);
    }

    public Rectangle entityArea(){
        return new Rectangle(x, y, gPanel.getTileSize(), gPanel.getTileSize());
    }
    public Rectangle entityNewArea(int newX, int newY){
        return new Rectangle(newX, newY, gPanel.getTileSize(), gPanel.getTileSize());
    }

    public void loadImage(String fileName) {
        try {
            playerImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeDirection(){}
    public void update(){
        changeDirection();
    }

    public void act() {
    }
}
