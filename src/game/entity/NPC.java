package game.entity;

import game.GPanel;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;



public class NPC extends Entity {
    protected String name;
    private long lastMoveTime;
    protected ArrayList<String> dialogues;
    protected int dialogIndex = 0;
    private final long moveInterval = 2000;
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speedP = 1;
        this.dialogues = new ArrayList<>();
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
    }

    public int getDialogIndex() {
        return dialogIndex;
    }

    public void setDialogIndex(int dialogIndex) {
        this.dialogIndex = dialogIndex;
    }

    public void setText(){
        if (gPanel.getCurrDialogIndex() == dialogIndex){
            if (dialogIndex < dialogues.size()){
                gPanel.getDrawStates().setCurrDialog(dialogues.get(dialogIndex));
                dialogIndex++;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("[a-zA-Z]{2,}")){
            this.name = name;
        }else {
            this.name = "npc";
        }

    }

    @Override
    public void update() {
        super.update();
        move();
    }

   @Override
    public void changeDirection() {

       long currentTime = System.currentTimeMillis();
       if (currentTime - lastMoveTime >= moveInterval) {
           Random random = new Random();
           int i = random.nextInt(4);
           switch (i) {
               case 0 -> this.direction = "up";
               case 1 -> this.direction = "down";
               case 2 -> this.direction = "left";
               case 3 -> this.direction = "right";
           }
           lastMoveTime = currentTime;
       }
    }



   /* public void move(){
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
    }*/

    public void move() {
        int newX = x;
        int newY = y;
        Random rd = new Random();
        switch (this.direction) {
            case "up" -> newY -= speedP;
            case "down" -> newY += speedP;
            case "left" -> newX -= speedP;
            case "right" -> newX += speedP;
        }

        if (checkCollision.hasCollision(direction, this)){
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

    public void setDialogues(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                dialogues.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "name: " + name;
    }



}
