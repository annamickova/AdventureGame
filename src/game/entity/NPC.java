package game.entity;

import game.GPanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;



public class NPC extends Entity {
    int counter = 0;
    String name;
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speedP = 1;
        getImage();
        setDialogues("npc.txt");
        System.out.println(dialogues);
    }

    private void getImage() {
        try {
            name = "karel";
            playerImage = ImageIO.read(new File("cat.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();
        move();
    }

    @Override
    public void act() {

        counter++;
        if (counter == 90){
            Random random = new Random () ;
            int i = random.nextInt (4);
            switch (i){
                case 0 -> this.direction = "up";
                case 1 -> this.direction = "down";
                case 2 -> this.direction = "left";
                case 3 -> this.direction = "right";
            }
            counter = 0;
        }



    }

    public void move(){
        int newX = x;
        int newY = y;

        switch (this.direction){
            case "up" -> newY -= speedP;
            case "down" -> newY += speedP;
            case "left" -> newX -= speedP;
            case "right" -> newX += speedP;
        }

        if (!collisionDetect.hasCollision(direction,newX,newY, this) && !collisionDetect.hit(this, gPanel.getPlayer())){
            x = newX;
            y = newY;
        }
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}
