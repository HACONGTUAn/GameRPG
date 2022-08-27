package game.ui;

import java.awt.Graphics;
import java.awt.Image;

public class UIImageBackground extends UIObject {
	
	private Image image;
	public UIImageBackground(int x, int y, int width, int height, Image image) {
		super(x, y, width, height);
		this.image = image;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y, width, height, null);
	}

	@Override
	public void onClick() {
		
	}
}