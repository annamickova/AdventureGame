package game;

import game.screenStates.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private GPanel gPanel;
    private Game game;
    public boolean moveUp;
    public boolean moveDown;
    public boolean moveLeft;
    public boolean moveRight;

    public KeyHandler(GPanel gPanel){
        this.gPanel = gPanel;
        this.game = gPanel.getGame();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.getGameState() == game.getPlay()){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> moveUp = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveDown = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveLeft = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveRight = true;
                case KeyEvent.VK_P -> game.setGameState(game.getStop());
                case KeyEvent.VK_F -> game.setGameState(game.getFunctions());
            }
            gPanel.getPlayer().setInteraction(true);
        }else if (game.getGameState() == game.getStop()){
             if (e.getKeyCode() == KeyEvent.VK_P){
                 game.setGameState(game.getPlay());
             }
        }else if (game.getGameState() == game.getFunctions()){
            switch (e.getKeyCode()){
                case KeyEvent.VK_F -> game.setGameState(game.getPlay());
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDrawStates()
                        .setFuncPointer(game.getDrawStates().getFuncPointer()-1);
                    game.getDrawStates().resetFuncPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDrawStates()
                        .setFuncPointer(game.getDrawStates().getFuncPointer()+1);
                    game.getDrawStates().resetFuncPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (gPanel.getCollectedItems().size()!= 0){
                        gPanel.getCollectedItems().get(game.getDrawStates().getFuncPointer()).action();
                        gPanel.getCollectedItems().remove(game.getDrawStates().getFuncPointer());
                    }
                }

            }

        }else if (game.getGameState() == game.getDialog()){
            if (e.getKeyCode() == KeyEvent.VK_M){
                gPanel.getPlayer().setInteraction(false);
                game.setGameState(game.getPlay());
            }
        }else if (game.getGameState() == game.getHome()) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDrawStates()
                        .setPointer(game.getDrawStates().getPointer()-1);
                    game.getDrawStates().resetPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDrawStates()
                        .setPointer(game.getDrawStates().getPointer()+1);
                    game.getDrawStates().resetPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (game.getDrawStates().getPointer() == 0){
                        game.setGameState(game.getPlay());
                    }
                    if (game.getDrawStates().getPointer() == 1){

                    }
                    if (game.getDrawStates().getPointer() == 2){
                        System.exit(0);
                    }
                }
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
