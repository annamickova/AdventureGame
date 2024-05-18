package game.entity;

import game.GPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;



public class NPC extends Entity {
   private String name;

    protected ArrayList<String> dialogues;
    protected int dialogIndex = 0;
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speedP = 1;
        this.dialogues = new ArrayList<>();
        name = "karel";
        loadImage("cat.jpeg");
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

    @Override
    public void update() {
        super.update();
        move();
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: " + name;
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


    public void setText(){
        if (gPanel.getCurrDialogIndex() == dialogIndex){
            if (dialogIndex < dialogues.size()){
                gPanel.getGame().getDrawStates().setCurrDialog(dialogues.get(dialogIndex));
                dialogIndex++;
            }
        }


    }

}
