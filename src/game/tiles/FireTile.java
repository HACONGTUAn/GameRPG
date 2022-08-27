package game.tiles;

import game.gfx.Assets;

public class FireTile extends Tile {
    public FireTile(int id) {
        super(Assets.fire, id);
        solid = false;
    }
}
