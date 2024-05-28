package game.Tests;

import game.GPanel;
import game.entity.Entity;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EntityTest {

    private GPanel gPanel = new GPanel();
    private Entity entity = new Entity(gPanel);

    @Test
    public void entityAreaAround() {
        try {
            entity.setX(gPanel.getTileSize());
            entity.setY(gPanel.getTileSize());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Rectangle rec = new Rectangle(0, 0, gPanel.getTileSize()*3, gPanel.getTileSize()*3);
        Rectangle result = entity.entityAreaAround();
        assertEquals(rec, result);
    }

    @Test
    public void entityArea() {
        try {
            entity.setX(gPanel.getTileSize());
            entity.setY(gPanel.getTileSize());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Rectangle rec = new Rectangle(entity.getX(), entity.getY(), gPanel.getTileSize(), gPanel.getTileSize());
        Rectangle result = entity.entityArea();
        assertEquals(rec, result);
    }
}