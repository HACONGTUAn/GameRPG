package game.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
    public boolean up, down, left, right;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}
	
}
