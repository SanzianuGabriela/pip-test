package Entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Collision;
import main.GamePanel;
import main.KeyInput;
public class Player extends Entity {
	GamePanel gp;
	KeyInput keyI;
	Collision coll;
	public Player(GamePanel gp,KeyInput keyI,int x,int y,int width,int height){
		super(gp,x,y,width,height);
		this.gp=gp;
		this.keyI=keyI;
		setDefaultValues();
		getPlayerImage();
		loadAnimations();
	}
	public void setDefaultValues(){
		ground=10*gp.tileSize;
		y=ground;
		x=gp.screenX;
		screenPX=gp.screenX;
		velosity=0;
		speed=8;
		direction="stay";
		gravity=1;
		lastDir="r";
	}
	public void loadAnimations(){
		animations=new BufferedImage[7][10];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				animations[i][j]=img.getSubimage(j*96,i*96,96,96);

			}
		}
	}
	public void AniUpdate(int i){
		aniTick++;
		if(aniTick >= aniSpeed){
			aniTick = 0;
			aniIndex++;
			if(aniIndex >=i)
				aniIndex=0;
		}
	}
	public void getPlayerImage(){
		try {
			img=ImageIO.read(getClass().getResource("/player/img3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(){
		updateHitbox();
		if (keyI.upPressed && !inAir) {
			velosity=-20;
			downStay=false;
			inAir=true;
		}
		if(!inAir)
			velosity=0;
		if (inAir) {
			if(velosity<=gp.tileSize/2-1)
				velosity += gravity;
			y += velosity;
			if(velosity<0)
				direction="up";
			else if(velosity>0)
				direction="fall";
			else
				direction="air";
			if(collisionDown) {
				y=(y/gp.tileSize)*gp.tileSize;
				inAir=false;
			}
		}
		if(!collisionDown)
			inAir=true;
		if(collisionUp) {
			velosity=5;
			}
		if(keyI.leftPressed==true){
			downStay=false;
			lastDir="l";
			if(!inAir)
				direction="left";
			if(!collisionLeft) {
				if(x>gp.screenX && screenPX<=gp.screenX)
					x-=speed;
				else if(x==gp.WorldWidth-30*gp.tileSize || x==gp.screenX)
					screenPX-=speed;
			}		


		}
		if(keyI.rightPressed==true){
			downStay=false;
			lastDir="r";
			if(!inAir)
				direction="right";
			if(!collisionRight) {
				if(screenPX==gp.screenX && x<gp.WorldWidth-30*gp.tileSize)
					x+=speed;
				else if(x==gp.WorldWidth-30*gp.tileSize || x==gp.screenX)
					screenPX+=speed;
			}		
				
		}
		if(!inAir && !keyI.rightPressed && !keyI.leftPressed) {
			if(!keyI.downPressed) {
				direction="stay";
				if(downStay)
					direction="down";
			}
			if(keyI.downPressed){
				direction="down";
				downStay=true;
				stayCount++;
			}

		}
		collisionUp=false;
		collisionDown=false;
		collisionLeft=false;
		collisionRight=false;
		gp.checkC.checkTile(this);

	}
	public void draw(Graphics2D g2){
		BufferedImage image =null;
		switch(direction){
		case "up":
			if(lastDir=="l")
				image=animations[5][0];
			if(lastDir=="r")
				image=animations[4][0];
			break;
		case "fall":
			if(lastDir=="l")
				image=animations[5][2];
			if(lastDir=="r")
				image=animations[4][2];
			break;
		case "air":
			if(lastDir=="l")
				image=animations[5][1];
			if(lastDir=="r")
				image=animations[4][1];
			break;
		case "left":
			nrFrames=8;
			AniUpdate(nrFrames);
			image=animations[2][aniIndex];
			break;
		case "right":
			nrFrames=8;
			AniUpdate(nrFrames);
			image=animations[3][aniIndex];
			break;
		case "stay":
			nrFrames=10;
			AniUpdate(nrFrames);
			if(lastDir=="r")
				image=animations[0][aniIndex];
			if(lastDir=="l")
				image=animations[1][aniIndex];
			break;
		case "down":
			if(downStay) {
				image=animations[6][1];
				if(keyI.downPressed) {
					stayCount=0;
					break;
				}
			}
			else {
				nrFrames=9;
				AniUpdate(nrFrames);
				image=animations[6][aniIndex];
			}
		}
		g2.drawImage(image,screenPX,y,gp.tileSize*12,gp.tileSize*12,null);
		drawHitbox(g2);
		drawPoints(g2);
	}
}
