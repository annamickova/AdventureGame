package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private GPanel gPanel;
    public boolean moveUp;
    public boolean moveDown;
    public boolean moveLeft;
    public boolean moveRight;

    public KeyHandler(GPanel gPanel){
        this.gPanel = gPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gPanel.getGame().getGameState() == gPanel.getGame().getPlay()){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> moveUp = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveDown = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveLeft = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveRight = true;
                case KeyEvent.VK_P -> gPanel.getGame().setGameState(gPanel.getGame().getStop());
            }
            gPanel.getPlayer().setInteraction(true);
        }
         else if (gPanel.getGame().getGameState() == gPanel.getGame().getStop()){
             if (e.getKeyCode() == KeyEvent.VK_P){
                 gPanel.getGame().setGameState(gPanel.getGame().getPlay());
             }
        }
         else if (gPanel.getGame().getGameState() == gPanel.getGame().getDialog()){
            if (e.getKeyCode() == KeyEvent.VK_M){
                gPanel.getPlayer().setInteraction(false);
                gPanel.getGame().setGameState(gPanel.getGame().getPlay());
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveUp = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveDown = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveLeft = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveRight = false;
        }
    }
}
