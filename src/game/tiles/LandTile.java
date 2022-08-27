package game.tiles;

import game.gfx.Assets;

public class LandTile extends Tile {
    public LandTile(int id) {
        super(Assets.land, id);
        solid = false;
    }
}
