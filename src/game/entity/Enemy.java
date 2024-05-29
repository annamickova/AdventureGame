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
        //creatureBackToLost();
        try {
            prisonCreature(this);
            prisonCreature(guard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

   /* private void creatureBackToLost(){
        int last = gPanel.getCaughtAnimals().size()-1;
        if (gPanel.getPlayer().entityArea().intersects(this.entityArea())){
            if (gPanel.getCaughtAnimals().size() != 0){
                gPanel.getLostAnimals().add(gPanel.getCaughtAnimals().get(last));
                gPanel.getCaughtAnimals().remove(last);
            }
        }
    }*/

    /**
     *
     * @param entity
     * @throws Exception
     */
    public void prisonCreature(Entity entity) throws Exception {
        for (int i = 0; i < gPanel.getLostAnimals().size(); i++) {
            if (gPanel.getLostAnimals().get(i).entityArea().intersects(entity.entityArea())){
                gPanel.getLostAnimals().get(i).setX(57*gPanel.getTileSize());
                gPanel.getLostAnimals().get(i).setY(2*gPanel.getTileSize());
            }
        }
    }

    public Guard getGuard() {
        return guard;
    }
}
