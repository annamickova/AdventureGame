package game.entity;

import game.GPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC extends Entity {
   private String name;

    protected ArrayList<String> dialogues;
    private long lastMoveTime;
    private final long moveInterval;
    protected int dialogIndex = 0;
    public NPC(GPanel gPanel) {
        super(gPanel);
        direction = "down";
        speedP = 1;
        this.dialogues = new ArrayList<>();
        moveInterval = 1200;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public int getDialogIndex() {
        return dialogIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
    public void setText(){
        if (gPanel.getCurrDialogIndex() == dialogIndex){
            if (dialogIndex < dialogues.size()){
                gPanel.getGame().getDrawStates().setCurrDialog(dialogues.get(dialogIndex));
                dialogIndex++;
            }
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
    public void update() {
        act();
        move();
    }

    public void move() {}

    public void act() {
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



}
