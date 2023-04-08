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
	public boolean collisionOn=false;
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
		hitbox=new Rectangle(x,y,width,height);

	}
	public void drawHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);
	}
	public void updateHitbox() {
		hitbox.y=y+96;

	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}
