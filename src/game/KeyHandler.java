package game;

import game.screenStates.Game;
import game.screenStates.GameState;

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
        if (game.getGameState() == GameState.PLAY){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> moveUp = true;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveDown = true;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveLeft = true;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveRight = true;
                case KeyEvent.VK_P -> game.setGameState(GameState.STOP);
                case KeyEvent.VK_F -> game.setGameState(GameState.FUNCTIONS);
            }
            gPanel.getPlayer().setInteraction(true);
        }else if (game.getGameState() == GameState.STOP){
             if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE){
                 game.setGameState(GameState.PLAY);
             }
        }else if (game.getGameState() == GameState.FUNCTIONS){
            switch (e.getKeyCode()){
                case KeyEvent.VK_F, KeyEvent.VK_ESCAPE -> game.setGameState(GameState.PLAY);
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDraw()
                        .setFuncPointer(game.getDraw().getFuncPointer()-1);
                    game.getDraw().resetFuncPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDraw()
                        .setFuncPointer(game.getDraw().getFuncPointer()+1);
                    game.getDraw().resetFuncPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (gPanel.getCollectedFunctionItems().size()!= 0){
                        gPanel.getCollectedFunctionItems().get(game.getDraw().getFuncPointer()).function();
                        gPanel.getCollectedFunctionItems().remove(game.getDraw().getFuncPointer());
                        game.setGameState(GameState.PLAY);
                        game.getDraw().setFuncPointer(0);

                    }
                }
            }

        } else if (game.getGameState() == GameState.DIALOG){
            if (e.getKeyCode() == KeyEvent.VK_M || e.getKeyCode() == KeyEvent.VK_ESCAPE){
                gPanel.getPlayer().setInteraction(false);
                game.setGameState(GameState.PLAY);
            }
        }else if (game.getGameState() == GameState.HOME) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDraw()
                        .setPointer(game.getDraw().getPointer()-1);
                    game.getDraw().resetPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDraw()
                        .setPointer(game.getDraw().getPointer()+1);
                    game.getDraw().resetPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (game.getDraw().getPointer() == 0){
                        game.setStartTime(System.nanoTime());
                        game.setGameState(GameState.PLAY);
                    }
                    if (game.getDraw().getPointer() == 1){
                        game.setGameState(GameState.DESCRIPTION);
                    }
                    if (game.getDraw().getPointer() == 2){
                        System.exit(0);
                    }
                }
            }
        } else if (game.getGameState() == GameState.DESCRIPTION) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                game.setGameState(GameState.PLAY);
            }


        } else if (game.getGameState() == GameState.END) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {game.getDraw()
                        .setPointer(game.getDraw().getPointer()-1);
                    game.getDraw().resetPointer();
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {game.getDraw()
                        .setPointer(game.getDraw().getPointer()+1);
                    game.getDraw().resetPointer();
                }
                case KeyEvent.VK_ENTER -> {
                    if (game.getDraw().getPointer() == 0){
                        GFrame frame = new GFrame();
                        game.setGameState(GameState.PLAY);
                    }
                    if (game.getDraw().getPointer() == 1){
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
