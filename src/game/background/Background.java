package game.background;

import game.GPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {

    private GPanel gPanel;
    private Tile[] tiles;
    private int[][] tileMap;

    public Background(GPanel gPanel) {
        this.gPanel = gPanel;
        this.tiles = new Tile[15];
        getTiles();
    }

    public void getTiles(){
        try {

            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(new File("grass4.jpg")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(new File("wall.jpg"))); ;
            tiles[1].setCollision(true);

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(new File("water.jpg")));
            tiles[2].setCollision(true);

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(new File("earth.jpg")));

            tiles[4] = new Tile();
            tiles[4].setImage(ImageIO.read(new File("tree.png")));
            tiles[4].setCollision(true);

            tiles[5] = new Tile();
            tiles[5].setImage(ImageIO.read(new File("sand.jpg")));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(tiles[0].getImage(), 0, 0, gPanel.getTileSize(),gPanel.getTileSize(), null);
        graphics2D.drawImage(tiles[1].getImage(), 48, 0, gPanel.getTileSize(),gPanel.getTileSize(), null);
        graphics2D.drawImage(tiles[2].getImage(), 96, 0, gPanel.getTileSize(),gPanel.getTileSize(), null);
    }
}
