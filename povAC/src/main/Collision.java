package main;

import java.awt.Color;

import Entity.Entity;

public class Collision {
	GamePanel gp;
	public Collision(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX=(int) (entity.x+3.5*gp.tileSize-gp.screenX+entity.screenPX);
		int entityRightWorldX=(int) (entityLeftWorldX+entity.hitbox.width+0.5*gp.tileSize);
		int entityTopWorldY=entity.y+4*gp.tileSize;
		if(entity.inAir) {
			entityTopWorldY=entity.y+gp.tileSize;
			}
		int entityBottomWorldY=entity.y+entity.hitbox.height+4*gp.tileSize;
		
		int entityLeftCol=entityLeftWorldX/gp.tileSize;
		int entityRightCol=entityRightWorldX/gp.tileSize;
		int entityTopRow=entityTopWorldY/gp.tileSize;
		int entityBottomRow=entityBottomWorldY/gp.tileSize;
		int tileNum;
		for (int i =entityLeftCol ; i < entityRightCol+1; i++) {
			for (int j = entityTopRow; j < entityBottomRow+1; j++) {
				if(j<36 && j>=0) {
					tileNum=gp.tileM.mapTileNum[i][j];
					if(gp.tileM.tile[tileNum].collision==true){
						if(j==entityTopRow) {
							entity.collisionUp=true;
							System.out.println("Colisiune Sus");
						}
						if(j==entityBottomRow && i!=entityLeftCol && i!=entityRightCol) {
							entity.collisionDown=true;
							System.out.println("Coliziune Jos");
						}
						if(i==entityLeftCol && j!=entityBottomRow) {
							entity.collisionLeft=true;
							System.out.println("Coliziune Stanga");
						}
						if(i==entityRightCol && j!=entityBottomRow) {
							entity.collisionRight=true;
							System.out.println("Coliziune Dreapta");
						}
						

					}
				}
			}
		}
		
	}
}
