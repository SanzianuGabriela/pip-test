package Entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;
public class Player extends Entity {
	GamePanel gp;
	KeyInput keyI;
	public Player(GamePanel gp,KeyInput keyI,int x,int y,int width,int height){
		super(x,y,width,height);
		this.gp=gp;
		this.keyI=keyI;
		setDefaultValues();
		getPlayerImage();
		loadAnimations();
	}
	public void setDefaultValues(){
		ground=10*gp.tileSize-1;
		y=ground;
		x=gp.screenX;
		velosity=-20;
		speed=8;
		direction="stay";
		gravity=1;
		lastDir="r";
	}
	public void loadAnimations(){
		animations=new BufferedImage[7][10];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				animations[i][j]=img.getSubimage((j*96),i*96,96,96);

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
			img=ImageIO.read(getClass().getResource("/player/img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(){
		updateHitbox();
		if (keyI.upPressed && y==ground) {
			if (downStay)
				velosity=(int) (velosity*1.5);
			downStay=false;
			inAir=true;
		}
		if (inAir) {
			velosity += gravity; 
			y += velosity;
			if(velosity<0)
				direction="up";
			else if(velosity>0)
				direction="fall";
			else
				direction="air";
			if(y>=ground){
				inAir=false;
				velosity=-20;
			}
		}
		if(keyI.leftPressed==true){
			downStay=false;
			lastDir="l";
			if(!inAir)
				direction="left";
			if(!collisionOn)
				x-=speed;
			
		}
		if(keyI.rightPressed==true){
			downStay=false;
			lastDir="r";
			if(!inAir)
				direction="right";
			if(!collisionOn)
				x+=speed;
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
		collisionOn=false;
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
		g2.drawImage(image,gp.screenX,y,gp.tileSize*6,gp.tileSize*6,null);
		drawHitbox(g2);
	}
}
