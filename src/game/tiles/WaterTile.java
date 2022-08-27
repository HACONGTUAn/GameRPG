package game.tiles;

import game.gfx.Assets;

public class WaterTile extends Tile {
    public WaterTile(int id) {
        super(Assets.water, id);
        solid = true;
    }
}
