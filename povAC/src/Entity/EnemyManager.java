package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Gamestates.Playing;
import Utils.LoadSave;
import static Utils.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] dogArr;
	private ArrayList<Dog> dogs = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		dogs = LoadSave.GetDogs();
		System.out.println("size of dogs: " + dogs.size());
	}

	public void update() {
		for (Dog c : dogs)
			c.update();
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawDogs(g, xLvlOffset);
	}

	private void drawDogs(Graphics g, int xLvlOffset) {
		for (Dog c : dogs)
			g.drawImage(dogArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset, (int) c.getHitbox().y, DOG_WIDTH, DOG_HEIGHT, null);

	}

	private void loadEnemyImgs() {
		dogArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.DOG_SPRITE);
		for (int j = 0; j < dogArr.length; j++)
			for (int i = 0; i < dogArr[j].length; i++)
				dogArr[j][i] = temp.getSubimage(i * DOG_WIDTH_DEFAULT, j * DOG_HEIGHT_DEFAULT, DOG_WIDTH_DEFAULT, DOG_HEIGHT_DEFAULT);
	}
}