package game.screens;

import java.awt.Graphics;

import game.entities.Entity;
import game.entities.Player;
import game.maps.Map;


public class GameScreen extends Screen{
	public static Map[] map = new Map[3];
	public static Map currentMap;
	public static int idMap = 0;
	
	public GameScreen() {
		
		map[0] = new Map("res/maps/map1.txt");
		map[1] = new Map("res/maps/map2.txt");
		map[2] = new Map("res/maps/map3.txt");
		
		currentMap = map[idMap];
	}

	@Override
	public void render(Graphics g) {
		currentMap.render(g);
	}
	
	public void setMap(Map m) {
		currentMap = m;
	}
	
	public static Map getMap() {
		return currentMap;
	}

}
