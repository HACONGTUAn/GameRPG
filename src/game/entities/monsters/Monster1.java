package game.entities.monsters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.Game;
import game.entities.Bullet;
import game.entities.Entity;
import game.entities.Player;
import game.gfx.Animation;
import game.gfx.Assets;
import game.screens.GameScreen;
import game.tiles.Tile;
import game.utils.HealthBar;
import game.utils.Utils;

public class Monster1 extends Monster {
	private final int LIMIT_TILE = 4;
	private int xStart, yStart;
	private boolean followTarget = false;

	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown, lastTime = 0;

	private ArrayList<Bullet> bullets;

	public Monster1(int x, int y) {
		super(x, y);

		speed = 2.0f;

		animationDown = new Animation(200, Assets.zombie1_down);
		animationUp = new Animation(200, Assets.zombie1_up);
		animationLeft = new Animation(200, Assets.zombie1_left);
		animationRight = new Animation(200, Assets.zombie1_right);
		currentAnimation = animationLeft;

		setMove();

		xStart = (int) (x / Tile.TILEWIDTH);
		yStart = (int) (y / Tile.TILEHEIGHT);

		healthBar = new HealthBar(this);

		bullets = new ArrayList<Bullet>();
	}

	public void setMove() {
		double angle;
		if (!followTarget) {
			if (System.currentTimeMillis() - lastTime >= 2000) {
				lastTime = System.currentTimeMillis();
				angle = Math.toRadians(Math.random() * 360);
				xMove = speed * (float) Math.cos(angle);
				yMove = speed * (float) Math.sin(angle);
			}
		} else {
			
			float rads = (float) Math.atan2(targetY - y, targetX - x);

			xMove = speed * (float) Math.cos(rads);
			yMove = speed * (float) Math.sin(rads);
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		float angle;
		if (!followTarget) {
			angle = (float) Math.atan2(yMove, xMove);
		}
		else {
			angle = (float) Math.atan2(targetY - y, targetX - x);
		}
		
		if ((3 * Math.PI / 4 < angle && angle < Math.PI) || (-Math.PI < angle && angle < -3 * Math.PI / 4)) {
			currentAnimation = animationLeft;
		} else if (-Math.PI / 4 < angle && angle < Math.PI / 4) {
			currentAnimation = animationRight;
		} else if (angle > -3 * Math.PI / 4 && angle < -Math.PI / 4) {
			currentAnimation = animationUp;
		} else if (Math.PI / 4 < angle && angle < 3 * Math.PI / 4) {
			currentAnimation = animationDown;
		}
		return currentAnimation.getCurrentFrame();
	}

	public void update() {
		setMove();
		currentAnimation.updateFrame();
		move();

		Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet b = it.next();
			b.update();
			if (!b.isActive())
				it.remove();
		}

		attack();

		healthBar.update();
	}

	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width * 2, height * 2, null);
		g.drawRect(x + bounds.x, y + bounds.y, bounds.width, bounds.height);

		healthBar.render(g);

		bullets.forEach((b) -> {
			if (b.isActive())
				b.render(g);
		});
	}

	@Override
	public void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		for (Entity e : GameScreen.currentMap.entityManager.getEntities()) {
			if (e instanceof Player) {
				if (Utils.distance(e.getX(), e.getY(), x, y) <= 170) {
					followTarget = true;
					targetX = e.getX();
					targetY = e.getY();
					if (isUp()) {
						bullets.add(new Bullet(this, x, y));
					} else if (isDown()) {
						bullets.add(new Bullet(this, x + bounds.x + bounds.width / 5,
								y + bounds.y + bounds.height + height / 2));

					} else if (isLeft()) {
						bullets.add(new Bullet(this, x - width / 2 - bounds.x, y + height / 4));
					} else if (isRight()) {
						bullets.add(new Bullet(this, x + width / 2 + bounds.width, y + height / 4));
					}
				} else {
					followTarget = false;
				}
				break;
			}
		}

		attackTimer = 0;
	}

	@Override
	public void move() {
		if (!checkEntityCollision(xMove, 0f))
			moveX();
		if (!checkEntityCollision(0f, yMove))
			moveY();
	}

	public void moveX() {
		int tx = 0;
		if (xMove > 0) { // Sang phai
			tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
		} else if (xMove < 0) { // Sang trai
			tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
		}
		if ((Math.abs(tx - xStart) < LIMIT_TILE) && (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
				&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))) {
			x += xMove;
		} else if (Math.abs(tx - xStart) >= LIMIT_TILE) {
			xMove = -xMove;
		} else {
			if (xMove > 0)
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			else if (xMove < 0)
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			xMove = -xMove;
		}
	}

	public void moveY() {
		int ty = 0;
		if (yMove > 0) { // Lên trên
			ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
		} else if (yMove < 0) { // Xuống dưới
			ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
		}
		if ((Math.abs(ty - yStart) < LIMIT_TILE) && (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
				&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))) {
			y += yMove;
		} else if (Math.abs(ty - yStart) >= LIMIT_TILE) {
			yMove = -yMove;
		} else {
			if (yMove > 0)
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
			else if (yMove < 0)
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			yMove = -yMove;
		}
	}
	
    public void die() {
//    	System.out.println("Die");
    }
}
