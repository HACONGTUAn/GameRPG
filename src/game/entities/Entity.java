package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.gfx.Animation;
import game.maps.Map;
import game.screens.GameScreen;
import game.tiles.Tile;
import game.utils.HealthBar;

public abstract class Entity{
    protected int x, y;
    protected int width, height;
    protected float health;
	protected float maxHealth;
	protected float def;
    public Rectangle bounds;
    protected boolean active = true;
    
	protected HealthBar healthBar;
    
    public static final float DEFAULT_SPEED = 2.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 24, 
                            DEFAULT_CREATURE_HEIGHT = 24;
    protected float speed;
    protected float xMove, yMove;
    
    public Animation animationDown, animationUp, animationLeft, animationRight, animationStand, currentAnimation;
  
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        speed = DEFAULT_SPEED;
        bounds = new Rectangle(0, 0, width, height);
        health = 100;
        maxHealth = 100;
        def = 20;
        xMove = 0;
        yMove = 0;
	}
	
	public abstract void render(Graphics g);
	public abstract void update();
	public abstract void move();
	public abstract void moveX();
	public abstract void moveY();
	
    public void die() {
   
    }
    
    public void hurt(int amt) {
        health -= amt;
        if(health <= 0) {
            active = false;
            die();
        }
    }
	
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
    
    protected boolean collisionWithTile(int x, int y) {
        return GameScreen.currentMap.getTile(x,y).isSolid();
    }
    
    
    public boolean checkEntityCollision(float xOffset, float yOffset) { 
        for(Entity e: GameScreen.currentMap.getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
//            	System.out.println("Va cham");
                return true;
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public boolean isLeft() {
        return currentAnimation==animationLeft;
    }
    
    public boolean isUp() {
        return currentAnimation==animationUp;
    }
    
    public boolean isRight() {
        return currentAnimation==animationRight;
    }
    
    public boolean isDown() {
        return currentAnimation==animationDown;
    }
}
