package game.entity;

import game.GPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC extends Entity {
    private String name;
    protected ArrayList<String> dialogues;
    protected long lastMoveTime;
    protected final long moveInterval;
    protected int dialogIndex = 0;
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speed = 1;
        dialogues = new ArrayList<>();
        moveInterval = 1200;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public int getDialogIndex() {
        return dialogIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(){
        if (gPanel.getCurrDialogIndex() == dialogIndex){
            if (dialogIndex < dialogues.size()){
                gPanel.getGame().getDrawStates().setCurrDialog(dialogues.get(dialogIndex));
                dialogIndex++;
            }
        }
    }

    /**
     * Loading speech for npc from text file.
     * @param fileName
     */
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
    public void update() {
        changeDirection();
        move();
    }

    /***
     * Generating random number to choose direction.
     * NPC moves if there is no collision.
     */
    public void move() {
        int newX = x;
        int newY = y;
        switch (this.direction) {
            case "up" -> newY -= speed;
            case "down" -> newY += speed;
            case "left" -> newX -= speed;
            case "right" -> newX += speed;
        }
        if (checkCollision.hasCollision(this)){
            Random random = new Random();
            int i = random.nextInt(4);
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
        checkCollision.entityTakesFuel(this);
    }

    /**
     * Changing npc direction every 2 seconds.
     */
    public void changeDirection() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= moveInterval) {
            Random random = new Random();
            int i = random.nextInt(4);
            switch (i) {
                case 0 -> direction = "up";
                case 1 -> direction = "down";
                case 2 -> direction = "left";
                case 3 -> direction = "right";
            }
            lastMoveTime = currentTime;
        }
    }



}
