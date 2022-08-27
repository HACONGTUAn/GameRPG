package game.manager;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Game;
import game.entities.Entity;
import game.entities.Player;
import game.entities.monsters.Monster;
import game.entities.monsters.Monster1;
import game.maps.Map;
import game.screens.GameScreen;


public class EntityManager {
	private ArrayList<Entity> entities;
	
	private Player player;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void render(Graphics g) {
		for(Entity e: entities) {
			e.render(g);
		}
	}
	
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
