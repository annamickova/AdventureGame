package game;

import game.screenStates.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final GPanel gPanel;
    private final Game game;
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

    /**
     * Handles key input in the game.
     * In play game state player can move, in other game states can stop game or display menu.
     * @param e the event to be processed
     */
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
             if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE){
                 game.setGameState(game.getPlay());
             }
        }else if (game.getGameState() == game.getFunctions()){
            switch (e.getKeyCode()){
                case KeyEvent.VK_F -> game.setGameState(game.getPlay());
                case KeyEvent.VK_ESCAPE -> game.setGameState(game.getPlay());
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDrawStates()
                        .setFuncPointer(game.getDrawStates().getFuncPointer()-1);
                    game.getDrawStates().resetFuncPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDrawStates()
                        .setFuncPointer(game.getDrawStates().getFuncPointer()+1);
                    game.getDrawStates().resetFuncPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (gPanel.getCollectedFunctionItems().size()!= 0){
                        gPanel.getCollectedFunctionItems().get(game.getDrawStates().getFuncPointer()).function();
                        gPanel.getCollectedFunctionItems().remove(game.getDrawStates().getFuncPointer());
                        game.setGameState(game.getPlay());
                        game.getDrawStates().setFuncPointer(0);

                    }
                }
            }

        } else if (game.getGameState() == game.getDialog()){
            if (e.getKeyCode() == KeyEvent.VK_M || e.getKeyCode() == KeyEvent.VK_ESCAPE){
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
                        game.setGameState(game.getDescription());
                    }
                    if (game.getDrawStates().getPointer() == 2){
                        System.exit(0);
                    }
                }
            }
        } else if (game.getGameState() == game.getDescription()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                game.setGameState(game.getPlay());
            }


        } else if (game.getGameState() == game.getIsEnd()) {
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
                        GFrame frame = new GFrame();
                        game.setGameState(game.getPlay());
                    }
                    if (game.getDrawStates().getPointer() == 1){
                        System.exit(0);
                    }
                }
            }
        }

    }

    /**
     * Setting moving directions to false, when key is released.
     * @param e the event to be processed
     */
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
