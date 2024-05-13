package game;

import java.awt.*;

public class DrawStates {
    private GPanel gPanel;
    private String currDialog;

    public DrawStates(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public String getCurrDialog() {
        return currDialog;
    }

    public void setCurrDialog(String currDialog) {
        this.currDialog = currDialog;
    }

    public void pauseScreen(Graphics2D graphics2D){
        String text = "PAUSED";
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 60));
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
        graphics2D.drawRoundRect(wX+5, wY+5, wWidth-10, wHeight -10, 20, 20);

        for (String line: currDialog.split("/")){
            graphics2D.drawString(line, wX + gPanel.getTileSize(), wY + gPanel.getTileSize());
            wY += gPanel.getTileSize();
        }
    }

    public void funcScreen(Graphics2D graphics2D){
        graphics2D.setColor(new Color(120,120,255,215));
        int wX = gPanel.getTileSize() * 7 - gPanel.getTileSize()/2;
        int wY = gPanel.getTileSize()/2;
        int wWidth = gPanel.getScreenWidth() - (gPanel.getTileSize()*9);
        int wHeight =gPanel.getTileSize() * 4;
        graphics2D.fillRoundRect(wX,wY,wWidth,wHeight, 20,20);

    }

    private boolean teleportUsed = false;

    public boolean isTeleportUsed() {
        return teleportUsed;
    }

    public void setTeleportUsed(boolean teleportUsed) {
        this.teleportUsed = teleportUsed;
    }

    public void teleport(){
        gPanel.getPlayer().setX(gPanel.getTileSize() * 21);
        gPanel.getPlayer().setY(gPanel.getTileSize() * 21);
    }

    public void walkThrough(){

    }

    public void func(){
        if (isTeleportUsed()){
            teleport();
        }
    }




}
