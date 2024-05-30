package game.entity;

import game.GPanel;

public class Enemy extends NPC{
    private Guard guard;
    public Enemy(GPanel gPanel) throws Exception{
        super(gPanel);
        setName("enemy");
        loadImage("pigeon.jpg");
        setX(gPanel.getTileSize()*22);
        setY(gPanel.getTileSize()*34);
        guard = new Guard(gPanel);

    }

    @Override
    public void update() {
        super.update();
        guard.update();
        try {
            prisonCreature(this);
            prisonCreature(guard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     *
     * @param entity
     * @throws Exception
     */
    public void prisonCreature(Entity entity) throws Exception {
        for (int i = 0; i < gPanel.getCreatures().size(); i++) {
            if (gPanel.getCreatures().get(i).entityArea().intersects(entity.entityArea())){
                gPanel.getCreatures().get(i).setX(57*gPanel.getTileSize());
                gPanel.getCreatures().get(i).setY(2*gPanel.getTileSize());
            }
        }
    }

    public Guard getGuard() {
        return guard;
    }
}
