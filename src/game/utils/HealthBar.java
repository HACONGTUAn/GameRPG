package game.utils;

import java.awt.Color;
import java.awt.Graphics;

import game.entities.Entity;
import game.entities.Player;

public class HealthBar {
	private Entity entity;
	private float healthPercent;
	private float maxHealth;
	private float width;
	long last = System.currentTimeMillis();

	public HealthBar(Entity entity) {
		this.entity = entity;
		maxHealth = entity.getMaxHealth();
		if (entity instanceof Player) {
			width = 26;
		} else {
			width = 45;
		}
	}

	public void render(Graphics g) {
		if (entity instanceof Player) {
			g.setColor(Color.green);
			g.fillRect((int) entity.getX() + entity.bounds.x - 8, (int) entity.getY() - 5, (int) healthPercent, 6);
			g.setColor(Color.black);
			g.drawRect((int) entity.getX() + entity.bounds.x - 8, (int) entity.getY() - 5, (int) width, 6);
		} else {
			g.setColor(Color.red);
			g.fillRect((int) entity.getX() + entity.bounds.x - 5, (int) entity.getY() - 6, (int) healthPercent, 6);
			g.setColor(Color.black);
			g.drawRect((int) entity.getX() + entity.bounds.x - 5, (int) entity.getY() - 6, (int) width, 6);
		}
	}

	public void update() {
		healthPercent = width * (entity.getHealth() / maxHealth);
//		if (System.currentTimeMillis() - last >= 1500) {
//			last = System.currentTimeMillis();
//			entity.setHealth((float) entity.getHealth() - 30);
//		}
	}
}
