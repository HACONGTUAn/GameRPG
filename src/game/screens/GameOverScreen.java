package game.screens;

import java.awt.Graphics;

import game.gfx.ImageLoader;

public class GameOverScreen extends Screen{

	@Override
	public void render(Graphics g) {
		g.drawImage(ImageLoader.loadImage("res/textures/gameover.jpg"), 0, 0, 1200, 720, null);
	}

}
