package game.tiles;

import game.gfx.Assets;

public class GachTile extends Tile {
    public GachTile(int id) {
        super(Assets.gach, id);
        solid = false;
    }
}
