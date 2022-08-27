package game.tiles;

import game.gfx.Assets;

public class CatTile extends Tile {
    public CatTile(int id) {
        super(Assets.cat, id);
        solid = false;
    }
}
