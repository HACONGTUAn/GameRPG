package game.tiles;

import game.gfx.Assets;

public class BiNgoTile extends Tile {
    public BiNgoTile(int id) {
        super(Assets.bi_ngo, id);
        solid = true;
    }
}
