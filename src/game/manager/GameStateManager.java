package game.manager;

import javax.xml.namespace.QName;

import game.Game;
import game.entities.Player;
import game.screens.GameScreen;
import game.screens.Screen;

public class GameStateManager {
	public static void update() {
		if (Screen.getScreen().equals(Game.gI.gameScreen)) {
			GameScreen.currentMap.entityManager.update();
		}
	}
}
