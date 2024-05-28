package game.Tests;

import game.GPanel;
import game.KeyHandler;
import game.entity.Player;
import game.items.Teleport;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeleportTest {
    private GPanel gPanel = new GPanel();

    private Player player = new Player(gPanel, new KeyHandler(gPanel));
    private Teleport teleport = new Teleport(gPanel);

    @Test
    public void function() {
        teleport.function();
        assertEquals(23*gPanel.getTileSize(), player.getX());
        assertEquals(21*gPanel.getTileSize(), player.getY());
    }

}