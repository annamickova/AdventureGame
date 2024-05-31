package game.tests;

import game.CheckCollision;
import game.GPanel;
import game.entity.Entity;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class CheckCollisionTest {
    private GPanel gPanel;
    private CheckCollision collision;
    private Entity e1;
    private Entity e2;

    @BeforeEach
    public void init(){
        gPanel = new GPanel();
        e1 = new Entity(gPanel);
        e2 = new Entity(gPanel);
        collision = new CheckCollision(gPanel);
        try {
            e1.setX(0);
            e1.setY(0);
            e1.setDirection("down");
            e2.setX(0);
            e2.setY(0);
            e2.setDirection("down");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void hasCollision() {
        init();
        boolean result = collision.hasCollision(e1);
        assertTrue(result);
    }

    @Test
    public void hit() {
        init();
        assertTrue(collision.hit(e1, e2));
    }

    @Test
    public void collisionWithout() {
        init();
        assertFalse(collision.collisionWithout(e1, "grass"));
    }
}