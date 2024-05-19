package game.entity;

import game.GPanel;

import java.util.Random;

public class Cat extends NPC{

    public Cat(GPanel gPanel) {
        super(gPanel);

        setName("cici");
        setDialogues("cat.txt");
        loadImage("cat.jpeg");
    }

    @Override
    public void move() {
        int newX = x;
        int newY = y;

        switch (this.direction) {
            case "up" -> newY -= speedP;
            case "down" -> newY += speedP;
            case "left" -> newX -= speedP;
            case "right" -> newX += speedP;
        }

        Random rd = new Random();
        if (checkCollision.hasCollision(this)){
            int i = rd.nextInt(4);
            switch (i) {
                case 0 -> direction = "up";
                case 1 -> direction = "down";
                case 2 -> direction = "left";
                case 3 -> direction = "right";
            }
        } else if (!checkCollision.hit(this, gPanel.getPlayer())) {
            x = newX;
            y = newY;
        }

    }
}
