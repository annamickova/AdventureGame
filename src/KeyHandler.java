import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPress;
    public boolean downPress;
    public boolean leftPress;
    public boolean rightPress;

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
