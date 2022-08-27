package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile wallTile = new WallTile(0);
    public static Tile fireTile = new FireTile(1);
    public static Tile waterTile = new WaterTile(2);
    public static Tile grassTile = new GrassTile(3);
    public static Tile landTile = new LandTile(4);
    public static Tile catTile = new CatTile(5);
    public static Tile land2Tile = new Land2Tile(6);
    public static Tile flowerTile = new FlowerTile(7);
    public static Tile bingoTile = new BiNgoTile(9);
    public static Tile gachTile = new GachTile(10);
    public static Tile gach2Tile = new Gach2Tile(11);
    public static Tile grass2Tile = new Grass2Tile(12);
    public static Tile xuongrongTile = new XuongRongTile(13);
    public static final int TILEWIDTH = 24, TILEHEIGHT = 24;
    
    protected BufferedImage texture;
    protected final int id;
    protected boolean solid;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    
    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }
}