package game.entity;

import game.GPanel;
import game.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    private GPanel gPanel;
    private KeyHandler keyHandler;

    public Player(GPanel gPanel, KeyHandler keyHandler) {
        this.gPanel = gPanel;
        this.keyHandler = keyHandler;

        x = 100;
        y = 100;
        speedP = 4;

    }

    public void getImage(){

    }

    public void update(){

        if (keyHandler.isUpPress() || keyHandler.downPress || keyHandler.isLeftPress() || keyHandler.isRightPress()){
            if (keyHandler.upPress){
                y -= speedP;
            } else if (keyHandler.downPress) {
                y += speedP;
            } else if (keyHandler.leftPress) {
                x -= speedP;
            }else if (keyHandler.rightPress){
                x += speedP;
            }
        }

    }
    public void draw(Graphics2D graphics2D){
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(x,y, gPanel.getTileSize(), gPanel.getTileSize());
    }

}
