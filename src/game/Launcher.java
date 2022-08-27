package game;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("Simple RPG", 1200, 720);
		game.start();
	}
}