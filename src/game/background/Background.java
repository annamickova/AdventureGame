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
        loadTiles();
        loadMap();
    }

    public Tile getTile(int col, int row) {
        int tileNum = tileMap[col][row];
        return tiles[tileNum];
    }

    private void loadMap() {

        try (BufferedReader br = new BufferedReader(new FileReader("map.txt"))) {
            for (int row = 0; row < gPanel.getMaxRow(); row++) {
                String line = br.readLine();
                String[] map = line.split(" ");
                for (int col = 0; col < gPanel.getMaxCol(); col++) {
                    int num = Integer.parseInt(map[col]);
                    tileMap[col][row] = num;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTiles() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(new File("grass4.jpg")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(new File("wall.jpg")));
            ;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // draws only visible tiles
    public void draw(Graphics2D graphics2D) {

        int leftTileCol = (gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX()) / gPanel.getTileSize();
        int topTileRow = (gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY()) / gPanel.getTileSize();


        int scTileX = gPanel.getScreenWidth() / gPanel.getTileSize() + 2;
        int scTileY = gPanel.getScreenHeight() / gPanel.getTileSize() + 2;

        for (int col = leftTileCol; col < leftTileCol + scTileX; col++) {
            for (int row = topTileRow; row < topTileRow + scTileY; row++) {
                if (col >= 0 && row >= 0 && col < gPanel.getMaxCol() && row < gPanel.getMaxRow()) {
                    int tileNum = tileMap[col][row];
                    int worldX = col * gPanel.getTileSize();
                    int worldY = row * gPanel.getTileSize();
                    int scX = worldX - (gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX());
                    int scY = worldY - (gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY());

                    graphics2D.drawImage(tiles[tileNum].getImage(), scX, scY, gPanel.getTileSize(),
                            gPanel.getTileSize(), null);
                }
            }
        }
    }



}