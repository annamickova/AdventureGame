package game.entity;

import game.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    protected GPanel gPanel;
    protected int x;
    protected int y;
    protected int speedP;

    protected BufferedImage playerImage;
    protected String direction;

    public Entity(GPanel gPanel) {
        this.gPanel = gPanel;
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

    public int getSpeedP() {
        return speedP;
    }

    public void setSpeedP(int speedP) {
        this.speedP = speedP;
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(BufferedImage playerImage) {
        this.playerImage = playerImage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void drawEntity(Graphics2D graphics2D){
        BufferedImage im = null;

        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY()+ gPanel.getPlayer().getScreenY();

        if (x + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY()){
            graphics2D.drawImage(playerImage, screenX, screenY, gPanel.getTileSize(), gPanel.getTileSize(), null);

        }
    }

    public void action(){}
    public void update(){
        action();
    }
}
