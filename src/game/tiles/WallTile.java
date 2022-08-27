package game.tiles;

import game.gfx.Assets;

public class WallTile extends Tile {

    public WallTile(int id) {
        super(Assets.wall, id);
        solid = true;
    }
}