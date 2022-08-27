package game.screens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.gfx.Assets;
import game.manager.UIManager;
import game.ui.ClickListener;
import game.ui.UIImageBackground;
import game.ui.UIImageButton;
import game.ui.UIObject;


public class MenuScreen extends Screen{
	private BufferedImage background;

	public MenuScreen() {
		UIManager.addObject(new UIImageBackground(0, 0, 1200, 720, Assets.background));
		UIManager.addObject(new UIImageButton(770, 500, 180, 150, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
				Screen.setScreen(Game.gI.gameScreen);
			}
			}));
	}
	
	@Override
	public void render(Graphics g) {
		UIManager.render(g);
	}
	
}
