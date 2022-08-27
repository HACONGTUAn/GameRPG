package game.utils;

import java.awt.Color;
import java.awt.Graphics;

import game.entities.Player;

public class MPBar {
	private Player player;
	private float mpPercent;
	private float maxMP;
	private float width;
//	private long last = System.currentTimeMillis();
	
//	private final Color LIGHT_BLUE = new Color(51,153,255);

	public MPBar(Player player) {
		this.player = player;
		maxMP = player.getMaxMP();
		width = 26;
	}

	public void render(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect((int) player.getX() + player.bounds.x - 8, (int) player.getY() + 3, (int) mpPercent, 4);
			g.setColor(Color.black);
			g.drawRect((int) player.getX() + player.bounds.x - 8, (int) player.getY() + 3, (int) width, 4);
	}

	public void update() {
		mpPercent = width * (player.getMP() / maxMP);
//		if (System.currentTimeMillis() - last >= 1500) {
//			last = System.currentTimeMillis();
//			entity.setHealth((float) entity.getHealth() - 30);
//		}
	}
}
