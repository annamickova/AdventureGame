package game.background;

import game.GPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Background {

    private GPanel gPanel;
    private Tile[] tiles;
    private int[][] tileMap;

    public Background(GPanel gPanel) {
        this.gPanel = gPanel;
        this.tiles = new Tile[15];
        this.tileMap = new int[60][60];
        getTiles();
        loadMap();
    }

    public void loadMap(){

        try (BufferedReader br = new BufferedReader(new FileReader("map.txt"))){
            int col = 0;
            int row = 0;

            while (col < gPanel.getMaxCol() && row < gPanel.getMaxRow()){
                String line = br.readLine();
                while (col < gPanel.getMaxCol()){
                    String[] map = line.split(" ");
                    int num = Integer.parseInt(map[col]);
                    tileMap[col][row] = num;
                    col++;
                }
                if (col == gPanel.getMaxCol()){
                    col = 0;
                    row++;
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        int col = 0;
        int row = 0;

        while (col < gPanel.getMaxCol() && row < gPanel.getMaxRow()){

            int mapX = col * gPanel.getTileSize();
            int mapY = row * gPanel.getTileSize();
            int scX = mapX - gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX();
            int scY = mapY - gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY();

            int tileNum = tileMap[col][row];

            if (mapX + gPanel.getTileSize() > gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX() &&
                mapX - gPanel.getTileSize() < gPanel.getPlayer().getX() + gPanel.getPlayer().getScreenX() &&
                mapY + gPanel.getTileSize() > gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY() &&
                mapY - gPanel.getTileSize() < gPanel.getPlayer().getY() + gPanel.getPlayer().getScreenY()){
                graphics2D.drawImage(tiles[tileNum].getImage(), scX, scY, gPanel.getTileSize(), gPanel.getTileSize(), null);
            }

            col++;

            if (col == gPanel.getMaxCol()){
                col = 0;
                row++;

            }
        }

    }


}
