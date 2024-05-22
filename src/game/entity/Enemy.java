package game.entity;

import game.GPanel;

public class Enemy extends NPC{
    public Enemy(GPanel gPanel) {
        super(gPanel);
        setName("enemy");
        loadImage("pigeon.jpg");
        setX(gPanel.getTileSize()*22);
        setY(gPanel.getTileSize()*34);
    }

    @Override
    public void update() {
        super.update();
        takeCreature();
    }

    private void takeCreature(){
        int last = gPanel.getCaughtAnimals().size()-1;
        if (gPanel.getPlayer().entityArea().intersects(this.entityArea())){
            if (gPanel.getCaughtAnimals().size() != 0){
                gPanel.getLostAnimals().add(gPanel.getCaughtAnimals().get(last));
                gPanel.getCaughtAnimals().remove(last);
            }
        }
    }
}
