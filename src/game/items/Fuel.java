package game.items;

import game.GPanel;

public class Fuel extends Item{

    private int size;
    public Fuel(GPanel gPanel) {
        super(gPanel);
        loadImage("assets/fuel.png");
        setName("fuel");
        size = 2;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = Math.max(size, 0);
    }
}
