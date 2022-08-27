package game.tiles;

import game.gfx.Assets;

public class Land3Tile extends Tile {
    public Land3Tile(int id) {
        super(Assets.land_3, id);
        solid = false;
    }
}
