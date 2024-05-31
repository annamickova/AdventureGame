package game.entity;

import game.GPanel;

import java.util.Random;

public class Ghost extends NPC{

    public Ghost(GPanel gPanel) {
        super(gPanel);
        setName("ghost");
        setDialogues("assets/ghost.txt");
        loadImage("assets/ghost.png");
        speed = 1;
    }

    /**
     * Random moving. He can move through all tiles except wall.
     */
    @Override
    public void move() {
        int newX = x;
        int newY = y;

        switch (this.direction) {
            case "up" -> newY -= speed;
            case "down" -> newY += speed;
            case "left" -> newX -= speed;
            case "right" -> newX += speed;
        }

        Random rd = new Random();
        if (checkCollision.collisionWithout(this, "wall")){
            int i = rd.nextInt(4);
            switch (i) {
                case 0 -> direction = "up";
                case 1 -> direction = "down";
                case 2 -> direction = "left";
                case 3 -> direction = "right";
            }
        } else {
            x = newX;
            y = newY;
        }

    }
}
