package game.tiles;

import game.gfx.Assets;

public class Land2Tile extends Tile {
    public Land2Tile(int id) {
        super(Assets.land_2, id);
        solid = false;
    }
}
