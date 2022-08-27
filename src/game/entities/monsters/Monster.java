package game.entities.monsters;

import java.awt.Graphics;

import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;
import game.utils.HealthBar;

public class Monster extends Entity {

	public int targetX = 0, targetY = 0;
	
	public Monster(int x, int y) {
		super(x, y, Entity.DEFAULT_CREATURE_WIDTH, Entity.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 6;
		bounds.y = 2;
		bounds.width = 34;
		bounds.height = 45;

		currentAnimation = new Animation(200, Assets.zombie_down);
		
		healthBar = new HealthBar(this);
	}

	@Override
	public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentFrame(), (int) x,
                (int) y , width * 2, height * 2, null);
        g.drawRect(x + bounds.x, y + bounds.y, bounds.width, bounds.height);
        
        healthBar.render(g);
	}

	public void update() {
		currentAnimation.updateFrame();

		move();
		
		healthBar.update();
	}

	public void move() {

	}

	@Override
	public void moveX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveY() {
		// TODO Auto-generated method stub
		
	}
	
	public void attack() {
		
	}
}
