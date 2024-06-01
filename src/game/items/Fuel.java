package game.items;

import game.GPanel;

public class Fuel extends Item{

    private int size;
    public Fuel(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/fuel.png");
        setName("fuel");
        size = 10;
    }

    public int getSize() {
        return size;
    }

    /**
     * Fuel capacity can't be smaller than 0.
     * @param size
     */
    public void setSize(int size) {
        if (size > 0){
           this.size = size;
        }else {
            this.size = 0;
        }
    }
}
