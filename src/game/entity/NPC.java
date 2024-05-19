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
        speedP = 2;
        this.dialogues = new ArrayList<>();
    }


    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public int getDialogIndex() {
        return dialogIndex;
    }

    @Override
    public void update() {
        super.update();
        move();
    }

    public void move() {

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
