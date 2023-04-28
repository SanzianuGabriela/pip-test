package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.io.InputStream;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public tiles[] tile;
	public int mapTileNum[][];
	public BufferedImage background;
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile =new tiles[10];
		mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap();
	}
	public void getTileImage() {
		try {
			background=ImageIO.read(getClass().getResource("/main/back.png"));
			tile[0]=new tiles();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			tile[1]=new tiles();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision=true;
			tile[2]=new tiles();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));
			tile[3]=new tiles();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {
			InputStream is=getClass().getResourceAsStream("/map/map.txt");
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
				String line1 =br.readLine();
				String line = line1.replace("\t"," ");
				while(col<gp.maxWorldCol) {
					String numbers[]=line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row]=num;
					col++;
				}
				if(col== gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int Worldrow=0;
		int PlayerCol=(int) (gp.player.x+3.5*gp.tileSize-gp.screenX+gp.player.screenPX)/gp.tileSize;
		int startCol=PlayerCol-42,finCol=PlayerCol+47;
		if(startCol<=0)
			startCol=0;
		if(finCol>=gp.maxWorldCol)
			finCol=gp.maxWorldCol;
		g2.drawImage(background,0-(gp.player.x+gp.screenX)/3,0,gp.WorldWidth/2+2*gp.tileSize/2+12*gp.tileSize,gp.WorldHeight,null);
		while(startCol <finCol && Worldrow <gp.maxWorldRow) {
			int tileNum = mapTileNum[startCol][Worldrow];
			int worldX=startCol*gp.tileSize;
			int worldY=Worldrow*gp.tileSize;
			int screenX=worldX-gp.player.x+gp.screenX;
			int screenY=worldY;
			g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			startCol++;
			if(startCol==finCol) {
				startCol=PlayerCol-42;
				if(startCol<=0)
					startCol=0;
				Worldrow++;
			}
		}
		
	}
}
