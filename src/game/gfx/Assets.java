package game.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import game.tiles.Tile;

public class Assets {
	private static final int width = 16, height = 16;
	private static final int widthChar = 64, heightChar = 64;
	public static Image background;
	public static BufferedImage wall, grass, land, water, fire, flower, land_2, land_3, bi_ngo, cat, gach, gach_2, grass_2, xuong_rong, player_bullet, monster_bullet;
	public static BufferedImage[] btn_start, player_down, player_up, player_left, player_right, player_stand;
	
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] zombie1_down, zombie1_up, zombie1_left, zombie1_right;

	public static void init() {
		try {
			SpriteSheet mapIcon = new SpriteSheet(ImageLoader.loadImage("res/maps/mapchip.png"));
			SpriteSheet charSpriteSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/player.png"));
		    SpriteSheet Monster = new SpriteSheet(ImageLoader.loadImage("res/textures/Monster.png"));

			background = new ImageIcon("res/textures/background.gif").getImage();
			btn_start = new BufferedImage[2];
			btn_start[0] = ImageIO.read(new File("res/textures/btn_start.png"));
			btn_start[1] = ImageIO.read(new File("res/textures/btn_start_hover.png"));
			
        	player_bullet = ImageLoader.loadImage("res/textures/player_bullet.png");
        	monster_bullet = ImageLoader.loadImage("res/textures/monster_bullet.png");
        	
			water = mapIcon.crop(width * 13, height * 12, width, height);
			grass = mapIcon.crop(width * 2, height * 9, width, height);
			wall = mapIcon.crop(width * 13, height * 17, width, height);
			land = mapIcon.crop(width * 4, height * 0, width, height);
			fire = mapIcon.crop(width * 13, height * 14, width, height);
			flower = mapIcon.crop(width * 15, height * 11, width, height);
			land_2 = mapIcon.crop(width * 5, height * 16, width, height);
			land_3 = mapIcon.crop(width * 13, height * 15, width, height);
			bi_ngo = mapIcon.crop(width * 7, height * 7, width, height);
			cat = mapIcon.crop(width * 9, height * 0, width, height);
			gach = mapIcon.crop(width * 7, height * 12, width, height);
			gach_2 = mapIcon.crop(width * 15, height * 21, width, height);
			grass_2 = mapIcon.crop(width * 4, height * 8, width, height);
			xuong_rong = mapIcon.crop(width * 9, height * 8, width, height);

			player_down = new BufferedImage[4];
			player_up = new BufferedImage[4];
			player_left = new BufferedImage[4];
			player_right = new BufferedImage[4];
			player_stand = new BufferedImage[4];

			player_down[0] = charSpriteSheet.crop(0 * widthChar, 0 * heightChar, widthChar, heightChar);
			player_down[1] = charSpriteSheet.crop(1 * widthChar, 0 * heightChar, widthChar, heightChar);
			player_down[2] = charSpriteSheet.crop(2 * widthChar, 0 * heightChar, widthChar, heightChar);
			player_down[3] = charSpriteSheet.crop(3 * widthChar, 0 * heightChar, widthChar, heightChar);
			
			player_left[0] = charSpriteSheet.crop(0 * widthChar, 1 * heightChar, widthChar, heightChar);
			player_left[1] = charSpriteSheet.crop(1 * widthChar, 1 * heightChar, widthChar, heightChar);
			player_left[2] = charSpriteSheet.crop(2 * widthChar, 1 * heightChar, widthChar, heightChar);
			player_left[3] = charSpriteSheet.crop(3 * widthChar, 1 * heightChar, widthChar, heightChar);

			player_right[0] = charSpriteSheet.crop(0 * widthChar, 2 * heightChar, widthChar, heightChar);
			player_right[1] = charSpriteSheet.crop(1 * widthChar, 2 * heightChar, widthChar, heightChar);
			player_right[2] = charSpriteSheet.crop(2 * widthChar, 2 * heightChar, widthChar, heightChar);
			player_right[3] = charSpriteSheet.crop(3 * widthChar, 2 * heightChar, widthChar, heightChar);

			player_up[0] = charSpriteSheet.crop(0 * widthChar, 3 * heightChar, widthChar, heightChar);
			player_up[1] = charSpriteSheet.crop(1 * widthChar, 3 * heightChar, widthChar, heightChar);
			player_up[2] = charSpriteSheet.crop(2 * widthChar, 3 * heightChar, widthChar, heightChar);
			player_up[3] = charSpriteSheet.crop(3 * widthChar, 3 * heightChar, widthChar, heightChar);

			player_stand[0] = charSpriteSheet.crop(0 * widthChar, 0 * heightChar, widthChar, heightChar);
			player_stand[1] = charSpriteSheet.crop(0 * widthChar, 1 * heightChar, widthChar, heightChar);
			player_stand[2] = charSpriteSheet.crop(0 * widthChar, 2 * heightChar, widthChar, heightChar);
			player_stand[3] = charSpriteSheet.crop(0 * widthChar, 3 * heightChar, widthChar, heightChar);
			
	        zombie_down = new BufferedImage[2];
	        zombie_down[0] 	= Monster.crop(width*12, height*8, width*2, height*2);
			zombie_down[1] 	= Monster.crop(width*14, height*8, width*2, height*2);
			
			zombie1_down = new BufferedImage[2];
			zombie1_up = new BufferedImage[2];
			zombie1_left = new BufferedImage[2];
			zombie1_right = new BufferedImage[2];

			zombie1_down[0] 	= Monster.crop(width * 0, height * 8, width*2, height*2);
			zombie1_down[1] 	= Monster.crop(width * 2, height * 8, width*2, height*2);
	        zombie1_up[0]   	= Monster.crop(width * 0, height * 14, width*2, height*2);
			zombie1_up[1]    = Monster.crop(width * 2, height * 14, width * 2, height * 2);
			zombie1_right[0] = Monster.crop(width * 0, height * 12, width * 2, height * 2);
			zombie1_right[1] = Monster.crop(width * 2, height * 12, width * 2, height * 2);
			zombie1_left[0] 	= Monster.crop(width * 0, height * 10, width * 2, height * 2);
			zombie1_left[1] 	= Monster.crop(width * 2, height * 10, width * 2, height * 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}