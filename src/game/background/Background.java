package game.background;

import game.GPanel;

import java.awt.*;
import java.io.*;

public class Background {

    private GPanel gPanel;
    private Tile[] tiles;
    private int[][] tileMap;

    public Background(GPanel gPanel) {
        this.gPanel = gPanel;
        this.tiles = new Tile[10];
        this.tileMap = new int[60][60];
        loadTiles();
        loadMap("assets/map.txt");
    }

    public Tile getTile(int col, int row) {
        int tileNum = tileMap[col][row];
        return tiles[tileNum];
    }

    private void loadMap(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

    /**
     * Creating new kinds of tiles and adding into tiles array.
     */
    public void loadTiles() {
        tiles[0] = new Tile();
        tiles[0].setName("grass");
        tiles[0].loadTileImage("assets/grass.png");

        tiles[1] = new Tile();
        tiles[1].setName("wall");
        tiles[1].loadTileImage("assets/wall.png");
        tiles[1].setCollision(true);

        tiles[2] = new Tile();
        tiles[2].setName("water");
        tiles[2].loadTileImage("assets/water.png");
        tiles[2].setCollision(true);

        tiles[3] = new Tile();
        tiles[3].setName("sand");
        tiles[3].loadTileImage("assets/sand.png");

        tiles[4] = new Tile();
        tiles[4].setName("tree");
        tiles[4].loadTileImage("assets/tree.png");
        tiles[4].setCollision(true);
    }

    /**
     *  Drawing only visible tiles.
     */
    public void draw(Graphics2D graphics2D) {
        int leftCol = (gPanel.getPlayer().getX() - gPanel.getPlayer().getScreenX()) / gPanel.getTileSize();
        int topRow = (gPanel.getPlayer().getY() - gPanel.getPlayer().getScreenY()) / gPanel.getTileSize();
        int x = gPanel.getScreenWidth() / gPanel.getTileSize() + 2;
        int y = gPanel.getScreenHeight() / gPanel.getTileSize() + 2;

        for (int col = leftCol; col < leftCol + x; col++) {
            for (int row = topRow; row < topRow + y; row++) {
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