package game.entity;

import game.CollisionDetect;
import game.GPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Entity {

    protected GPanel gPanel;
    protected int x;
    protected int y;
    protected int speedP;
    protected CollisionDetect collisionDetect;

    protected BufferedImage playerImage;
    protected String direction;
    protected boolean interaction;
    protected ArrayList<String> dialogues;
    protected int counter = 0;
    protected int dialogIndex = 0;

    public Entity(GPanel gPanel) {
        this.gPanel = gPanel;
        this.collisionDetect = new CollisionDetect(gPanel);
        this.dialogues = new ArrayList<>();
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInteraction() {
        return interaction;
    }

    public void setInteraction(boolean interaction) {
        this.interaction = interaction;
    }

    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
    }



    public void drawEntity(Graphics2D graphics2D){
        int screenX = x - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
        int screenY = y - gPanel.getPlayer().getY()+ gPanel.getPlayer().getScreenY();

        if (x + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                x - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                y + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                y - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY()){
            graphics2D.drawImage(playerImage, screenX, screenY, gPanel.getTileSize(), gPanel.getTileSize(), null);

        }
    }

    public Rectangle entityAreaAround(){
        Rectangle rec = new Rectangle(x-gPanel.getTileSize(), y-gPanel.getTileSize(),
                gPanel.getTileSize()*3, gPanel.getTileSize()*3);
        return rec;
    }

    public Rectangle entityArea(){
        Rectangle rec = new Rectangle(x, y, gPanel.getTileSize(), gPanel.getTileSize());
        return rec;
    }
    public Rectangle entityNewArea(int newX, int newY){
        Rectangle rec = new Rectangle(newX, newY, gPanel.getTileSize(), gPanel.getTileSize());
        return rec;
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
        if (dialogIndex < dialogues.size()){
            gPanel.getDrawStates().setCurrDialog(dialogues.get(dialogIndex));
            dialogIndex++;
        }

    }
    public void act(){}
    public void update(){
        act();
    }
}
