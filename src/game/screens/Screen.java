package game.screens;

import java.awt.Graphics;

public abstract class Screen {
	private static Screen currentScreen = null;
	public static void setScreen(Screen screen) {
		currentScreen = screen;
	}
	
	public static Screen getScreen() {
		return currentScreen;
	}
	
	public abstract void render(Graphics g);
}
