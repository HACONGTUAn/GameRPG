package game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class  UIObject {
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds; 
	protected boolean hovering = false;
	
	public UIObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int)x, (int)y, width, height);
	}
	
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	public void onMouseClicked(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY()))
			onClick();
	}
	
	public void onMouseMove(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
}
