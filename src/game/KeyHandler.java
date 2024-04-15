package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPress;
    private boolean downPress;
    private boolean leftPress;
    private boolean rightPress;

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
            case KeyEvent.VK_W-> upPress = true;
            case KeyEvent.VK_UP-> upPress = true;
            case KeyEvent.VK_S-> downPress = true;
            case KeyEvent.VK_DOWN-> downPress = true;
            case KeyEvent.VK_A-> leftPress = true;
            case KeyEvent.VK_LEFT-> leftPress = true;
            case KeyEvent.VK_D-> rightPress = true;
            case KeyEvent.VK_RIGHT-> rightPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_W-> upPress = false;
            case KeyEvent.VK_UP-> upPress = false;
            case KeyEvent.VK_S-> downPress = false;
            case KeyEvent.VK_DOWN-> downPress = false;
            case KeyEvent.VK_A-> leftPress = false;
            case KeyEvent.VK_LEFT-> leftPress = false;
            case KeyEvent.VK_D-> rightPress = false;
            case KeyEvent.VK_RIGHT-> rightPress = false;
        }
    }
}
