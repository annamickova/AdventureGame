package game.screenStates;

import game.GPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class drawing screens states.
 */
public class DrawStates {
    private final GPanel gPanel;
    private String currDialog;
    private int pointer;
    private final int textCount;
    private final String font = "Rockwell";
    private int funcPointer;
    private int funcCount;
    private String timeS;

    public DrawStates(GPanel gPanel) {
        this.gPanel = gPanel;
        this.textCount = 3;
        this.pointer = 0;
        this.funcPointer = 0;
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
        graphics2D.setColor(Color.white);
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
        dialogueText(graphics2D,wX,wY,wWidth,wHeight);
    }
    private void dialogueText(Graphics2D graphics2D, int wX, int wY, int wWidth, int wHeight){
        graphics2D.setColor(new Color(0,0,0));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setFont(new Font(font, Font.BOLD, 12));
        graphics2D.drawRoundRect(wX+5, wY+5, wWidth-10, wHeight -10, 20, 20);
        for (String line: currDialog.split("/")){
            graphics2D.drawString(line, wX + gPanel.getTileSize(), wY + gPanel.getTileSize());
            wY += gPanel.getTileSize();
        }
    }

    /**
     * Drawing home screen with menu.
     * @param graphics2D
     */
    public void homeScreen(Graphics2D graphics2D){
        String text = "game";
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
        String menuT2 = "game description";
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

    /**
     * Drawing screen with game description.
     * @param graphics2D
     */
    public void gameDescriptionScreen(Graphics2D graphics2D){
        String text = "game description";
        graphics2D.setFont(new Font(font, Font.BOLD, 50));
        int x = gPanel.getScreenWidth()/2;
        int y = gPanel.getTileSize()*4;
        graphics2D.setColor(new Color(70,80,120));
        graphics2D.fillRect(0,0, gPanel.getScreenWidth(), gPanel.getScreenHeight());
        graphics2D.setColor(Color.white);
        graphics2D.drawString(text,  x - textCentred(graphics2D, text)/2,y);
        int leftX = (x-x/2)-gPanel.getTileSize()*3;
        loadAndDrawText(graphics2D, leftX, y+gPanel.getTileSize());
    }

    /**
     * Loading text from file and drawing on screen.
     * @param graphics2D
     * @param x
     * @param y
     */
    private void loadAndDrawText(Graphics2D graphics2D, int x, int y) {
        try (BufferedReader br = new BufferedReader(new FileReader("assets/game.txt"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine())!= null) {
                drawText(graphics2D, x, y + lineNumber*gPanel.getTileSize(), line, 25);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Screen drawing when game ends.
     * @param graphics2D
     */
    public void gameOverScreen(Graphics2D graphics2D){
        String gameOver = "game over";
        String text = "";
        graphics2D.setFont(new Font(font, Font.BOLD, 50));
        int x = gPanel.getScreenWidth()/2;
        int hX =  x - textCentred(graphics2D, gameOver)/2;
        int hY = gPanel.getTileSize()*4;
        String sc = "Score: ";
        if (gPanel.getGame().isVictory()){
            text = "you found all your missing fuel";
            if (gPanel.getGame().getScore() >= gPanel.getGame().newScore()){
                sc = "New score: ";
            }
        } else {
            text = "you lost, try again";
            gPanel.getGame().setScore(0);
        }

        sc += gPanel.getGame().getScore();

        graphics2D.setColor(new Color(70,80,120));
        graphics2D.fillRect(0,0, gPanel.getScreenWidth(), gPanel.getScreenHeight());
        graphics2D.setColor(new Color(70,80,120));
        graphics2D.setColor(Color.white);
        graphics2D.drawString(gameOver, hX,hY);
        graphics2D.setFont(new Font(font, Font.BOLD, 30));
        graphics2D.drawString(text, x - textCentred(graphics2D,text)/2,hY+3*gPanel.getTileSize()/2);
        graphics2D.drawString(sc, x - textCentred(graphics2D, sc)/2,hY+3*gPanel.getTileSize());
        String newGame = "new game";
        String ending = "end";

        graphics2D.setFont(new Font(font, Font.BOLD, 50));
        graphics2D.drawString(newGame, x - textCentred(graphics2D,newGame)/2,hY+5*gPanel.getTileSize());
        graphics2D.drawString(ending, x - textCentred(graphics2D,ending)/2,hY+7*gPanel.getTileSize());

        switch (pointer){
            case 0 -> drawText(graphics2D, (x - textCentred(graphics2D,newGame)/2) - gPanel.getTileSize(),
                    hY+5*gPanel.getTileSize(), "•", 50);
            case 1 -> drawText(graphics2D, (x - textCentred(graphics2D,ending)/2) - gPanel.getTileSize(),
                    hY+7*gPanel.getTileSize(), "•", 50);
        }
    }

    private void drawText(Graphics2D graphics2D, int hX, int mY, String text, int size){
        graphics2D.setFont(new Font(font, Font.BOLD, size));
        graphics2D.drawString(text, hX, mY);
    }

    /**
     * Method for calculating coordinate for centred text.
     * @param graphics2D
     * @param text
     * @return
     */
    private int textCentred(Graphics2D graphics2D, String text){
       return (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
    }

    /**
     * Reset pointer for home screen menu.
     */
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

    /**
     * Drawing rectangle for functions screen.
     * @param graphics2D
     */
    public void smallScreen(Graphics2D graphics2D){
        graphics2D.setColor(new Color(255,255,255,200));
        int wX = gPanel.getTileSize() * 13 - gPanel.getTileSize()/2;
        int wY = gPanel.getTileSize()/2;
        int wWidth = gPanel.getTileSize()*3;
        int wHeight =gPanel.getTileSize()*gPanel.getCollectedFunctionItems().size()*2+gPanel.getTileSize();
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);
    }

    /**
     * Drawing two parts - functions and background.
     * @param graphics2D
     */
    public void funcScreen(Graphics2D graphics2D){
        smallScreen(graphics2D);
        availableFunc(graphics2D);
    }

    /**
     * Displaying all available functions images with label.
     * Player can use arrows to choose function, pressing enter function begins.
     * @param graphics2D
     */
    private void availableFunc(Graphics2D graphics2D){
        int x = gPanel.getTileSize()*13;
        int y = gPanel.getTileSize();
        graphics2D.setColor(Color.BLACK);
        funcCount = gPanel.getCollectedFunctionItems().size();
        if (funcCount != 0){
            if (funcCount > gPanel.getCollectedFunctionItems().size()){
                funcCount = 0;
            }
                for (int i = 0; i < gPanel.getCollectedFunctionItems().size(); i++) {
                    graphics2D.drawImage(gPanel.getCollectedFunctionItems().get(i).getItemImage(), x, y,gPanel.getTileSize(), gPanel.getTileSize(), null);
                    y += 3*gPanel.getTileSize()/2;
                    drawText(graphics2D, x, y, gPanel.getCollectedFunctionItems().get(i).getName(), 15);
                    if (funcPointer == i){
                        drawText(graphics2D, x - 8 , y, "•", 12);
                    }
                    y += gPanel.getTileSize()/2;
                }
        }else {
            drawText(graphics2D, x,gPanel.getTileSize() , "no functions", 15);
        }
    }

    /**
     * Drawing how many litres player needs to find to win.
     * @param graphics2D
     */
    public void lostFuel(Graphics2D graphics2D){
        graphics2D.setColor(new Color(255,255,255,200));
        int wX = gPanel.getTileSize()/2;
        int wY = gPanel.getTileSize()/2;
        int wWidth = 5*gPanel.getTileSize()/2;
        int wHeight = 3*gPanel.getTileSize()/2;
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);
        int x = gPanel.getTileSize();
        int y = gPanel.getTileSize()-gPanel.getTileSize()/8;
        graphics2D.setColor(new Color(0,0,0,255));
        int need = 10;
        for (int i = 0; i < gPanel.getCollectedItems().size(); i++) {
            need -= gPanel.getCollectedItems().get(i).getSize();
        }
        gPanel.getGame().setFuelNeed(need);
        drawText(graphics2D, x-8, gPanel.getTileSize()/2 + wHeight/2 , "find:", 15);
        drawText(graphics2D, x-8, gPanel.getTileSize() + wHeight/2 , gPanel.getGame().getFuelNeed()+"l ", 15);
            try {
                graphics2D.drawImage(ImageIO.read(new File("assets/fuel.png")),x+gPanel.getTileSize(),y, 3*gPanel.getTileSize()/4, 3*gPanel.getTileSize()/4, null);
            } catch (IOException e) {
               e.printStackTrace();

            }
        livesCount(graphics2D);
    }

    /**
     * Drawing current count of lives.
     * @param graphics2D
     */
    public void livesCount(Graphics2D graphics2D){
        graphics2D.setColor(new Color(255,255,255,200));
        int wX = 3*gPanel.getTileSize();
        int wY = gPanel.getTileSize()/2;
        int wWidth = 3*gPanel.getTileSize()/4*(gPanel.getPlayer().getLives()+2);
        int wHeight = 3*gPanel.getTileSize()/2;
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);

        int x = 7*gPanel.getTileSize()/2;
        int y = gPanel.getTileSize()-gPanel.getTileSize()/8;

        for (int i = 0; i < gPanel.getPlayer().getLives(); i++) {
            graphics2D.drawImage(gPanel.getPlayer().getLivesImage(), x, y, 3*gPanel.getTileSize()/4,3*gPanel.getTileSize()/4,null);
            x += gPanel.getTileSize();
        }
    }

    /**
     * Drawing current time on screen.
     * @param graphics2D
     */
    public void currTime(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(font, Font.BOLD, 30));
        timeS = String.format("%02d:%02d", gPanel.getGame().getMinutes(), gPanel.getGame().getSeconds());
        graphics2D.drawString(timeS, gPanel.getScreenWidth()/2 - gPanel.getTileSize()/2, 3*gPanel.getTileSize()/2);
    }


}
