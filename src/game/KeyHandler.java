package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPress;
    public boolean downPress;
    public boolean leftPress;
    public boolean rightPress;

    public boolean isUpPress() {
        return upPress;
    }

    public void setUpPress(boolean upPress) {
        this.upPress = upPress;
    }

    public boolean isDownPress() {
        return downPress;
    }

    public void setDownPress(boolean downPress) {
        this.downPress = downPress;
    }

    public boolean isLeftPress() {
        return leftPress;
    }

    public void setLeftPress(boolean leftPress) {
        this.leftPress = leftPress;
    }

    public boolean isRightPress() {
        return rightPress;
    }

    public void setRightPress(boolean rightPress) {
        this.rightPress = rightPress;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPress = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPress = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPress = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPress = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPress = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPress = false;
        }
    }
}
