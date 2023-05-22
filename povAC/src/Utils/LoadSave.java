package Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import Entity.Dog;
import static Utils.Constants.EnemyConstants.DOG;
import main.Game;

public class LoadSave {
	public static final String BACKGROUND="resources/back.png";
	public static final String PLAYER_ATLAS = "resources/assets2.png";
	public static final String LEVEL_ATLAS = "resources/outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "resources/level_one_data.png";
	public static final String MENU_BUTTONS = "resources/button_atlas.png";
	public static final String MENU_BACKGROUND = "resources/menu_background.png";
	public static final String PAUSE_BACKGROUND = "resources/pause_menu.png";
	public static final String SOUND_BUTTONS = "resources/sound_button.png";
	public static final String URM_BUTTONS = "resources/urm_buttons.png";
	public static final String VOLUME_BUTTONS = "resources/volume_buttons.png";
	public static final String DOG_SPRITE = "resources/caine.png";
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static ArrayList<Dog> GetDogs() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Dog> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == DOG)
					list.add(new Dog(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}

	public static int[][] GetLevelData() {
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}
