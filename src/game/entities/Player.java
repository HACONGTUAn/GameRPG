package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;

import game.Game;
import game.gfx.Animation;
import game.gfx.Assets;
import game.maps.Map;
import game.screens.GameOverScreen;
import game.screens.GameScreen;
import game.screens.GameWinScreen;
import game.screens.Screen;
import game.tiles.Tile;
import game.utils.HealthBar;
import game.utils.MPBar;

public class Player extends Entity {
	private int direction = 0;
	private float mp = 100;
	private float maxMP = 100;
	
	private ArrayList<Bullet> bullets;
	private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
	private long lastUpdateMP, updateMPCooldown = 2000;
	public static Player player;
	
	private MPBar mpBar;

	public Player(int x, int y) {
		super(x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 18;
		bounds.y = 40;
		bounds.width = 10;
		bounds.height = 4;

		animationDown = new Animation(200, Assets.player_down);
		animationUp = new Animation(200, Assets.player_up);
		animationLeft = new Animation(200, Assets.player_left);
		animationRight = new Animation(200, Assets.player_right);
		animationStand = new Animation(200, Assets.player_stand);

		currentAnimation = animationDown;

		player = this;

		healthBar = new HealthBar(this);
		mpBar = new MPBar(this);
		bullets = new ArrayList<Bullet>();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), x, y, width * 2, height * 2, null);
		g.drawRect(x + bounds.x, y + bounds.y, bounds.width, bounds.height);
//		g.setColor(Color.red);
//		g.drawRect((int)pos.x, (int)pos.y, 32, 32);
//		g.setColor(Color.YELLOW);

		healthBar.render(g);
		mpBar.render(g);
		bullets.forEach((b) -> {
			b.render(g);
		});
	}

	public void update() {
		currentAnimation.updateFrame();

		getInput();
		move();

		Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet b = it.next();
			b.update();
			if (!b.isActive()) {
				it.remove();
			}

		}

		updateMP();
		attack();
		collisionWithMonster();

		healthBar.update();
		mpBar.update();
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			direction = 1;
			currentAnimation = animationLeft;
		} else if (xMove > 0) {
			direction = 2;
			currentAnimation = animationRight;
		} else if (yMove < 0) {
			direction = 3;
			currentAnimation = animationUp;
		} else if (yMove > 0) {
			direction = 0;
			currentAnimation = animationDown;
		} else if (xMove == 0 && yMove == 0) {
			currentAnimation = animationStand;
			switch (direction) {
			case 0:
				return animationStand.getFrames()[0];
			case 1:
				return animationStand.getFrames()[1];
			case 2:
				return animationStand.getFrames()[2];
			case 3:
				return animationStand.getFrames()[3];
			default:
				return animationStand.getFrames()[0];
			}
		}

		return currentAnimation.getCurrentFrame();
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (Game.gI.getKeyManager().up)
			yMove -= speed;
		if (Game.gI.getKeyManager().down)
			yMove += speed;
		if (Game.gI.getKeyManager().left)
			xMove -= speed;
		if (Game.gI.getKeyManager().right)
			xMove += speed;
	}

	public void move() {
		if (!checkEntityCollision(xMove, 0f))
			moveX();
		if (!checkEntityCollision(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) { // Sang phải
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (tx > 49) {
				if (GameScreen.idMap == 2) {
					Screen.setScreen(new GameWinScreen());
					return;
				}
				((GameScreen) Game.gI.gameScreen).setMap(GameScreen.map[++GameScreen.idMap]);
				GameScreen.currentMap.player.setX(GameScreen.currentMap.startX);
				GameScreen.currentMap.player.setY(GameScreen.currentMap.startY);
				return;
			}
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		} else if (xMove < 0) { // Sang trái
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (tx < 0) {
				((GameScreen) Game.gI.gameScreen).setMap(GameScreen.map[--GameScreen.idMap]);
				GameScreen.currentMap.player.setX(GameScreen.currentMap.endX);
				GameScreen.currentMap.player.setY(GameScreen.currentMap.endY);
				return;
			}
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x + 1;
			}
		}
	}

	public void moveY() {
		if (yMove < 0) { // Lên trên
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
			}
		} else if (yMove > 0) { // Xuống dưới
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	public int getDirection() {
		return direction;
	}

	public void attack() {
		if (Game.gI.getMouseManager().isFire) {
			attackTimer += System.currentTimeMillis() - lastAttackTimer;
			lastAttackTimer = System.currentTimeMillis();
			if (attackTimer < attackCooldown)
				return;
			if(mp < 0)
				return;
			int mouseX = Game.gI.getMouseManager().mouseX;
			int mouseY = Game.gI.getMouseManager().mouseY;
			float angle = (float) Math.atan2(mouseY - y, mouseX - x);

			if ((3 * Math.PI / 4 < angle && angle < Math.PI) || (-Math.PI < angle && angle < -3 * Math.PI / 4)) {
				direction = 1;
			} else if (-Math.PI / 4 < angle && angle < Math.PI / 4) {
				direction = 2;
			} else if (Math.PI / 4 < angle && angle < 3 * Math.PI / 4) {
				direction = 0;
			} else if (angle >  -3 * Math.PI / 4 && angle < -Math.PI / 4) {
				direction = 3;
			}

			if (direction == 3) { // Up
				bullets.add(new Bullet(this, x + width / 2, y));

			} else if (direction == 0) { // Down
				bullets.add(new Bullet(this, x + width / 2, y + bounds.y + bounds.height));

			} else if (direction == 1) { // Left
				bullets.add(new Bullet(this, x - width / 2, y + height / 2));

			} else if (direction == 2) { // Right
				bullets.add(new Bullet(this, x + bounds.x + bounds.width + width / 4, y + height / 2));
			}
			mp -= 25;
		}
		attackTimer = 0;
	}

	public void collisionWithMonster() {
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 4;
		ar.width = arSize;
		ar.height = arSize;

		if (direction == 3) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (direction == 0) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (direction == 1) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize;
		} else if (direction == 2) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else
			return;

		for (Entity e : GameScreen.currentMap.entityManager.getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				this.hurt(1);
				return;
			}
		}
	}

	public void die() {
		Screen.setScreen(new GameOverScreen());
	}
	
	public float getMaxMP() {
		return maxMP;
	}
	
	public float getMP() {
		return mp;
	}
	
	public void updateMP() {
		if (System.currentTimeMillis() - lastUpdateMP >= updateMPCooldown && mp < maxMP) {
			lastUpdateMP = System.currentTimeMillis();
			mp += 25;
		}
	}
}
