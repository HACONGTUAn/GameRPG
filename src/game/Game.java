package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.script.ScriptEngine;

import game.entities.Player;
import game.gfx.Assets;
import game.manager.GameStateManager;
import game.manager.KeyManager;
import game.manager.MouseManager;
import game.screens.GameScreen;
import game.screens.MenuScreen;
import game.screens.Screen;

public class Game extends Thread implements Runnable {
	public Display display;
	private int width, height;

	private BufferStrategy bs;
	public Graphics g;

	// Screen
	public Screen gameScreen;
	public Screen menuScreen;

	// input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	public static Game gI;

	public Game(String title, int width, int height) {		
		this.width = width;
		this.height = height;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();

		Assets.init();

		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		menuScreen = new MenuScreen();
		gameScreen = new GameScreen();

		Screen.setScreen(menuScreen);
		
		gI = this;
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);

		// Draw here
		if (Screen.getScreen() != null)
			Screen.getScreen().render(g);
		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void update() {
		GameStateManager.update();
	}

	@Override
	public void run() {
		while (true) {
			int fps = 60;
			double timePerTick = 1000000000/fps;
			double delta = 0;
			long now;
			long lastTime = System.nanoTime();
			long timer = 0;
			int ticks = 0;
			while(true) {
				now = System.nanoTime();
				delta += (now - lastTime)/timePerTick;
				timer += now - lastTime;
				lastTime = now;
				if(delta >= 1) {
					update();
					render();
					ticks++;
					delta--;
				}
				if(timer >= 1000000000 ) {
//					System.out.println("Ticks and Frames:" + ticks );
					ticks = 0;
					timer = 0;
				}
				
			}
		}
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
}
