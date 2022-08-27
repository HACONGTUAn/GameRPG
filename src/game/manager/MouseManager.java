package game.manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Game;
import game.maps.Map;
import game.screens.GameScreen;
import game.screens.Screen;

public class MouseManager implements MouseListener, MouseMotionListener {

	public boolean isFire = false;
	public int mouseX = 0, mouseY = 0;
	
	public MouseManager() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Screen.getScreen().equals(Game.gI.menuScreen)) {
			UIManager.onMouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Screen.getScreen().equals(Game.gI.gameScreen)) {
			mouseX = e.getX();
			mouseY = e.getY();
			isFire = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Screen.getScreen().equals(Game.gI.gameScreen)) {
			isFire = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		UIManager.onMouseMove(e);
	}
}
