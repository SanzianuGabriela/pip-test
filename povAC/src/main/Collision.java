package main;

import java.awt.Color;

import Entity.Entity;

public class Collision {
	GamePanel gp;
	public Collision(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX=entity.x+2*gp.tileSize;
		int entityRightWorldX=entity.x+entity.hitbox.width+2*gp.tileSize;
		int entityTopWorldY=entity.y+2*gp.tileSize;
		if(entity.inAir) {
			entityTopWorldY=entity.y+gp.tileSize;
			}
		int entityBottomWorldY=entity.y+entity.hitbox.height+2*gp.tileSize;
		
		int entityLeftCol=entityLeftWorldX/gp.tileSize;
		int entityRightCol=entityRightWorldX/gp.tileSize;
		int entityTopRow=entityTopWorldY/gp.tileSize;
		int entityBottomRow=entityBottomWorldY/gp.tileSize;
		int tileNum;
		for (int i =entityLeftCol ; i < entityRightCol+1; i++) {
			for (int j = entityTopRow; j < entityBottomRow+1; j++) {
				if(j<18 && j>=0) {
					tileNum=gp.tileM.mapTileNum[i][j];
					if(gp.tileM.tile[tileNum].collision==true){
						if(j==entityTopRow) {
							entity.collisionUp=true;
							System.out.println("Sus");
						}
						if(j==entityBottomRow) {
							entity.collisionDown=true;
							//System.out.println("Jos");
						}
						if(i==entityLeftCol && j!=entityBottomRow) {
							entity.collisionLeft=true;
							System.out.println("Stanga");
						}
						if(i==entityRightCol && j!=entityBottomRow) {
							entity.collisionRight=true;
							System.out.println("Dreapta");
						}
						

					}
				}
			}
		}
		
	}
}
