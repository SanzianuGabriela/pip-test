package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
	GamePanel gp;
	public int x,y,speed,worldX;
	public BufferedImage[][] animations;
	public BufferedImage img;
	public String direction,lastDir;
	int gravity,ground;
	boolean inAir=false,downStay=false;
	public int spriteCounter=-5,velosity;
	public int spriteNum=1;
	public int aniTick,aniIndex, aniSpeed =10,nrFrames,stayCount;
	public Rectangle hitbox;

	public int width,height;
	public Entity(int x,int y,int width ,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		initHitbox();
	} 
	public void initHitbox() {
		hitbox=new Rectangle(x,y,width/2,height);

	}
	public void drawHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);
	}
	public void updateHitbox() {
		if(downStay) {
			hitbox.x=x+85;
			hitbox.height=height/2;
			hitbox.y=y+150;
		}
		else if(inAir) {
			hitbox.x=x+85;
			hitbox.y=y+45;
			hitbox.height=height-100;
			if(lastDir=="r")
				hitbox.x=x+110;
				
		}
		else {
			hitbox.x=x+85;
			hitbox.y=y+70;
			hitbox.height=height-70;
			hitbox.width=width/2;
		}
	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}
