package game.tiles;

import game.gfx.Assets;

public class Grass2Tile extends Tile {
    public Grass2Tile(int id) {
        super(Assets.grass_2, id);
        solid = false;
    }
}
