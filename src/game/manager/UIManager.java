package game.manager;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.ui.UIObject;


public class UIManager {
	private static ArrayList<UIObject> objects = new ArrayList<UIObject>();
	
	public static void render(Graphics g) {
		for(UIObject o: objects)
			o.render(g);
	}
	
	public static void onMouseClicked(MouseEvent e) {
		for(UIObject o: objects)
			o.onMouseClicked(e);
	}
	
	public static void onMouseMove(MouseEvent e) {
		for(UIObject o: objects)
			o.onMouseMove(e);
	}
	
	public static void addObject(UIObject o) {
		objects.add(o);
	}
	public static void removeObject(UIObject o) {
		objects.remove(o);
	}
	
	public static void removeAllObject() {
		for(UIObject o: objects)
			removeObject(o);
	}
}


