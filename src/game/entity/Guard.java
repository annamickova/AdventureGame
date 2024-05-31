package game.entity;

import game.GPanel;

import java.util.Random;

public class Guard extends NPC{
    public Guard(GPanel gPanel) {
        super(gPanel);
        setName("guard");
        speed = 2;
        loadImage("assets/spider.png");
        try {
            setX(gPanel.getTileSize()*54);
            setY(gPanel.getTileSize()*13);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Guard's movement. Changing position by choosing random directory left or right.
     */
    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        Random rd = new Random();
        if (currentTime - lastMoveTime >= moveInterval) {
            int i = rd.nextInt(2);
            switch (i) {
                case 0 -> this.direction = "left";
                case 1 -> this.direction = "right";
            }
            lastMoveTime = currentTime;
        }
        int newX = x;

        switch (this.direction) {
            case "left" -> newX -= speed;
            case "right" -> newX += speed;
        }

        if (checkCollision.hasCollision(this)){
            int i2 = rd.nextInt(2);
            switch (i2) {
                case 0 -> direction = "left";
                case 1 -> direction = "right";
            }

        } else {
            x = newX;

        }
        checkCollision.npcMovePlayer(this);
    }


}
