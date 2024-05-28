package game.items;

import game.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    protected GPanel gPanel;
    protected String name;
    protected BufferedImage itemImage;

    protected int itemX;
    protected int itemY;
    protected Rectangle area;

    public Item(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public int getItemX() {
        return itemX;
    }

    public void setItemX(int itemX) {
        this.itemX = itemX;
    }

    public int getItemY() {
        return itemY;
    }

    public void setItemY(int itemY) {
        this.itemY = itemY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getItemImage() {
        return itemImage;
    }

    public Rectangle itemArea() {
        area = new Rectangle(itemX, itemY,gPanel.getTileSize(), gPanel.getTileSize() );
        return area;
    }

    private boolean isOnScreen(){
        return itemX + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                itemX - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                itemY + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                itemY - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();
    }

    /**
     * Drawing visible items on screen.
     * @param g2
     * @param gPanel
     */
    public void drawItem(Graphics2D g2, GPanel gPanel){
        int screenX = itemX - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = itemY - gPanel.getPlayer().getY()+ gPanel.getPlayer().getScreenY();
        if (isOnScreen()){
            g2.drawImage(itemImage, screenX, screenY, gPanel.getTileSize(), gPanel.getTileSize(), null);
        }
    }

    public void function(){}

}
