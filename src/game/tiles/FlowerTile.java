package game.tiles;

import game.gfx.Assets;

public class FlowerTile extends Tile {
    public FlowerTile(int id) {
        super(Assets.flower, id);
        solid = false;
    }
}
