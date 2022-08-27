package game.maps;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

import game.entities.Player;
import game.entities.monsters.Monster;
import game.entities.monsters.Monster1;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.gfx.SpriteSheet;
import game.manager.EntityManager;
import game.screens.GameScreen;
import game.tiles.Tile;
import game.utils.Utils;

public class Map {
	private int width, height;
	public int startX, startY;
	public int endX, endY;

	public static Player player = new Player(0, 0);

	private int[][] tiles;

	// Entities
	public EntityManager entityManager;

	public Map(String path) {
		loadMap(path);

		entityManager = new EntityManager();
		entityManager.addEntity(new Monster(100, 200));
		entityManager.addEntity(new Monster1(150, 100));
		if (Map.player.getX() == 0 && Map.player.getY() == 0) {
			player.setX(startX);
			player.setY(startY);
		}
		entityManager.addEntity(player);
	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * 24, y * 24);
			}
		}

		if (GameScreen.idMap != 2) {
			g.drawImage(new ImageIcon("res/textures/next.gif").getImage(), endX + 10, endY + 10, 48, 48, null);
		}
		if (GameScreen.idMap != 0) {
			g.drawImage(new ImageIcon("res/textures/previous.gif").getImage(), startX, startY, 48, 48, null);
		}

//        if(isClicked) {
//        	g.drawImage(Assets.player_bullet, mouseX - 10, mouseY - 12, 24, 24, null);
//        	g.drawRect(mouseX - 10, mouseY - 12, 24, 24);
//        	g.drawString(".", mouseX, mouseY);
//        	isClicked = true;
//        }

		// items
//		itemManager.render(g);
		// entities
		entityManager.render(g);
	}

	private void loadMap(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		startX = Utils.parseInt(tokens[2]);
		startY = Utils.parseInt(tokens[3]);
		endX = Utils.parseInt(tokens[4]);
		endY = Utils.parseInt(tokens[5]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 6]);
			}
		}
	}

	public Tile getTile(int x, int y) {
		return Tile.tiles[this.tiles[x][y]];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
