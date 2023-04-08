package main;

import Entity.Entity;

public class Collision {
	GamePanel gp;
	public Collision(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX=entity.x+2*gp.tileSize;
		int entityRightWorldX=entity.x+entity.hitbox.width+2*gp.tileSize;
		int entityTopWorldY=entity.y;
		int entityBottomWorldY=entity.y+entity.hitbox.height;
		
		int entityLeftCol=entityLeftWorldX/gp.tileSize;
		int entityRightCol=entityRightWorldX/gp.tileSize;
		int entityTopRow=entityTopWorldY/gp.tileSize;
		int entityBottomRow=entityBottomWorldY/gp.tileSize;
		int tileNum1,tileNum2;
		switch(entity.direction) {
		case"up":
			entityTopRow=(entityTopWorldY -entity.speed)/gp.tileSize;
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
				entity.collisionOn=true;
				System.out.println("Coliziune SUS");
			}
			break;
		case"fall":
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
				entity.collisionOn=true;
				System.out.println("Coliziune JOS");
			}
			break;
		case "left":
			entityLeftCol=(entityLeftWorldX -entity.speed)/gp.tileSize;
			tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
				entity.collisionOn=true;
				System.out.println("Coliziune Stanga");
		
			}
			break;
		case"right":
			tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
				entity.collisionOn=true;
				System.out.println("Coliziune Dreapta");
			}
			break;
		}
		if(entity.collisionOn)
			System.out.println("Colizion");
	}
}
