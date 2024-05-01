package game.items;

import game.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    protected String name;
    protected BufferedImage itemImage;

    protected int itemX;
    protected int itemY;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
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
        if (name.matches("^[a-zA-Z]{2,}$")){
            this.name = name;
        }else{
            this.name = "item";
        }
    }


    @Override
    public String toString() {
        return name;
    }

    public void drawItem(Graphics2D g2, GPanel gPanel){

        int screenX = itemX - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = itemY - gPanel.getPlayer().getY()+ gPanel.getPlayer().getScreenY();

        if (itemX + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                itemX - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                itemY + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                itemY - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY()){
            g2.drawImage(itemImage, screenX, screenY, gPanel.getTileSize(), gPanel.getTileSize(), null);
        }


    }

}
