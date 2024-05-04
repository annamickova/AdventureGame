package game;

import java.awt.*;

public class DrawStates {
    private GPanel gPanel;

    public DrawStates(GPanel gPanel) {
        this.gPanel = gPanel;
    }

    public void pauseScreen(Graphics2D graphics2D){
        String text = "PAUSED";
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 60));
        int l = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gPanel.getScreenWidth()/2 - l/2;
        int y = gPanel.getScreenHeight()/2 - gPanel.getTileSize();

        graphics2D.drawString(text, x, y);
    }
}
