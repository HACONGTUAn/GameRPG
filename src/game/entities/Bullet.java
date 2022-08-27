package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.entities.monsters.Monster;
import game.gfx.Assets;
import game.maps.Map;
import game.screens.GameScreen;
import game.tiles.Tile;

public class Bullet extends Entity {

	private Entity owner;
	public static final float BULLET_SPEED = 5.0f;

	private int X, Y;

	public Bullet(Entity owner, int x, int y) {
		super(x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);
		this.owner = owner;
		speed = BULLET_SPEED;

		X = x;
		Y = y;

		bounds.x = -10;
		bounds.y = -12;
		bounds.width = 24;
		bounds.height = 24;

		if (owner instanceof Player)
			setMovePlayerBullet();
		else if (owner instanceof Monster) {
			setMoveMonsterBullet();
		}
	}

	private void setMovePlayerBullet() {
		int mouseX = Game.gI.getMouseManager().mouseX;
		int mouseY = Game.gI.getMouseManager().mouseY;

		float rads = (float) Math.atan2(mouseY - y, mouseX - x);

		xMove = BULLET_SPEED * (float) Math.cos(rads);
		yMove = BULLET_SPEED * (float) Math.sin(rads);
	}

	private void setMoveMonsterBullet() {
		float rads = (float) Math.atan2(((Monster) owner).targetY - y, ((Monster) owner).targetX - x);

		xMove = BULLET_SPEED * (float) Math.cos(rads);
		yMove = BULLET_SPEED * (float) Math.sin(rads);
		
	}

	@Override
	public void render(Graphics g) {
		if (owner instanceof Player) {
			g.drawImage(Assets.player_bullet, x, (int) y, bounds.width, bounds.height, null);
//			g.drawLine(X, Y, mouseX, mouseY);
		} else if (owner instanceof Monster) {
			g.drawImage(Assets.monster_bullet, x, (int) y, bounds.width, bounds.height, null);
		}
		g.drawRect(x, y, bounds.width, bounds.height);
	}

	@Override
	public void update() {
		move();
		checkAttacks();
	}

	@Override
	public void move() {
		if (!checkEntityCollision(xMove, 0f))
			moveX();
		else {
			active = false;
		}
		if (!checkEntityCollision(0f, yMove))
			moveY();
		else {
			active = false;
		}
	}

	@Override
	public void moveX() {
		if (Math.abs(X - x) > 250) {
			active = false;
		} else if (xMove > 0) { // Tiếp tục di chuyển sang phai nếu không chạm vào tile
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else { // Di chuyển đến sát bound của tile và biến mất
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width + 1;
				active = false;
			}
		} else if (xMove < 0) { // Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x - 1;
				active = false;
			}
		}
	}

	@Override
	public void moveY() {
		if (Math.abs(Y - y) > 250) {
			active = false;
		} else if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
				active = false;

			}
		} else if (yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				active = false;

			}
		}
	}
	
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0, 0);
        if(this.owner instanceof Monster) {
            for(Entity e : GameScreen.currentMap.entityManager.getEntities()) {
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                    if(e.equals(Map.player)) {
                    	Map.player.hurt(25);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }else if(this.owner instanceof Player) {
            for(Entity e : GameScreen.currentMap.entityManager.getEntities()) {
                if(e.equals(this) || e.equals(this.owner))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                        e.hurt(25);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }

}
