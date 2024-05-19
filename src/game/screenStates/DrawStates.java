package game.screenStates;

import game.GPanel;

import java.awt.*;

public class DrawStates {
    private GPanel gPanel;
    private String currDialog;
    private int pointer;
    private int textCount;
    private String font = "Rockwell";
    private int funcPointer;
    private int funcCount;

    public DrawStates(GPanel gPanel) {
        this.gPanel = gPanel;
        this.textCount = 3;
        this.pointer = 0;
        this.funcPointer = 0;
    }

    public String getCurrDialog() {
        return currDialog;
    }

    public void setCurrDialog(String currDialog) {
        this.currDialog = currDialog;
    }

    public int getFuncPointer() {
        return funcPointer;
    }

    public void setFuncPointer(int funcPointer) {
        this.funcPointer = funcPointer;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public void pauseScreen(Graphics2D graphics2D){
        String text = "PAUSED";
        graphics2D.setFont(new Font(font, Font.BOLD, 60));
        int l = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gPanel.getScreenWidth()/2 - l/2;
        int y = gPanel.getScreenHeight()/2 - gPanel.getTileSize();

        graphics2D.drawString(text, x, y);
    }

    public void dialogScreen(Graphics2D graphics2D){
        graphics2D.setColor(new Color(250,250,250,215));
        int wX = gPanel.getTileSize() * 6 - gPanel.getTileSize()/2;
        int wY = gPanel.getTileSize()/2;
        int wWidth = gPanel.getScreenWidth() - (gPanel.getTileSize()*6);
        int wHeight =gPanel.getTileSize() * 4;
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);

        displayDialogue(graphics2D,wX,wY,wWidth,wHeight);

    }

    private void displayDialogue(Graphics2D graphics2D, int wX, int wY, int wWidth, int wHeight){
        graphics2D.setColor(new Color(0,0,0));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setFont(new Font(font, Font.BOLD, 12));
        graphics2D.drawRoundRect(wX+5, wY+5, wWidth-10, wHeight -10, 20, 20);

        for (String line: currDialog.split("/")){
            graphics2D.drawString(line, wX + gPanel.getTileSize(), wY + gPanel.getTileSize());
            wY += gPanel.getTileSize();
        }
    }


    public void homeScreen(Graphics2D graphics2D){
        String text = "hello";
        graphics2D.setFont(new Font(font, Font.BOLD, 50));

        int x = gPanel.getScreenWidth()/2;
        int hX =  x - textCentred(graphics2D, text)/2;
        int hY = gPanel.getTileSize()*4;
        graphics2D.setColor(new Color(70,80,120));
        graphics2D.fillRect(0,0, gPanel.getScreenWidth(), gPanel.getScreenHeight());

        graphics2D.setColor(Color.black);
        graphics2D.drawString(text, hX+5,hY+5);

        graphics2D.setColor(Color.white);
        graphics2D.drawString(text, hX,hY);
        String menuT1 = "new game";
        String menuT2 = "continue";
        String menuT3 = "end";

        drawText(graphics2D, x - textCentred(graphics2D,menuT1)/2, hY+3*gPanel.getTileSize(), menuT1,50);
        drawText(graphics2D, x - textCentred(graphics2D,menuT2)/2, hY+5*gPanel.getTileSize(), menuT2,50);
        drawText(graphics2D, x - textCentred(graphics2D,menuT3)/2, hY+7*gPanel.getTileSize(), menuT3,50);

        switch (pointer){
            case 0 -> drawText(graphics2D, (x - textCentred(graphics2D,menuT1)/2) - gPanel.getTileSize(),
                    hY+3*gPanel.getTileSize(), "•", 50);
            case 1 -> drawText(graphics2D, (x - textCentred(graphics2D,menuT2)/2) - gPanel.getTileSize(),
                    hY+5*gPanel.getTileSize(), "•", 50);
            case 2 -> drawText(graphics2D, (x - textCentred(graphics2D,menuT3)/2) - gPanel.getTileSize(),
                    hY+7*gPanel.getTileSize(), "•", 50);
        }
    }

    private void drawText(Graphics2D graphics2D, int hX, int mY, String text, int size){
        graphics2D.setFont(new Font(font, Font.BOLD, size));
        graphics2D.drawString(text, hX, mY);
    }

    private int textCentred(Graphics2D graphics2D, String text){
       return (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
    }

    public void resetPointer(){
        if (pointer < 0){
            pointer = textCount-1;
        }
        if (pointer > textCount-1){
            pointer = 0;
        }
    }
    public void resetFuncPointer(){
        if (funcPointer < 0){
            funcPointer = funcCount-1;
        }
        if (funcPointer > funcCount-1){
            funcPointer = 0;
        }
    }


    public void smallScreen(Graphics2D graphics2D){
        graphics2D.setColor(new Color(255,255,255,255));
        int wX = gPanel.getTileSize() * 13 - gPanel.getTileSize()/2;
        int wY = gPanel.getTileSize()/2;
        int wWidth = gPanel.getScreenWidth() - (gPanel.getTileSize()*13);
        int wHeight = gPanel.getTileSize() * 7;
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);
    }

    public void funcScreen(Graphics2D graphics2D){
        smallScreen(graphics2D);
        availableFunc(graphics2D);
    }

    private void availableFunc(Graphics2D graphics2D){
        int x = gPanel.getTileSize()*13;
        int y = gPanel.getTileSize();
        graphics2D.setColor(Color.BLACK);
        funcCount = gPanel.getCollectedItems().size();
        if (funcCount != 0){
            if (funcCount > gPanel.getCollectedItems().size()){
                funcCount = 0;
            }
                for (int i = 0; i < gPanel.getCollectedItems().size(); i++) {
                    graphics2D.drawImage(gPanel.getCollectedItems().get(i).getItemImage(), x, y,gPanel.getTileSize(), gPanel.getTileSize(), null);
                    y += 3*gPanel.getTileSize()/2;
                    drawText(graphics2D, x, y, gPanel.getCollectedItems().get(i).getName(), 15);
                    if (funcPointer == i){
                        drawText(graphics2D, x - 8 , y, "•", 12);
                    }
                    y += gPanel.getTileSize()/2;
                }
        }else {
            drawText(graphics2D, x,gPanel.getTileSize() * 7/2 , "no functions", 15);
        }
    }

    public void animalsScreen(Graphics2D graphics2D){
        smallScreen(graphics2D);
        int x = gPanel.getTileSize()*13;
        int y = gPanel.getTileSize();
        graphics2D.setColor(Color.BLACK);
        int animalCount = gPanel.getCaughtAnimals().size();
        if (animalCount != 0){
            for (int i = 0; i < animalCount; i++) {
                graphics2D.drawImage(gPanel.getCaughtAnimals().get(i).getEntityImage(), x, y,gPanel.getTileSize(), gPanel.getTileSize(), null);
                y += 3*gPanel.getTileSize()/2;
                drawText(graphics2D, x, y, gPanel.getCaughtAnimals().get(i).getName(), 15);
                y += gPanel.getTileSize()/2;
                drawText(graphics2D, x,y , gPanel.getLostAnimals().size() + " left animals", 15);
                y += gPanel.getTileSize()/2;
            }
        }else {
            drawText(graphics2D, x,gPanel.getTileSize() * 7/2 , "no animals", 15);
            drawText(graphics2D, x,gPanel.getTileSize() * 9/2 , gPanel.getLostAnimals().size() + " animals left", 15);
        }
    }






}
