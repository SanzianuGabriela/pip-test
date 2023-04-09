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
	public int gravity;
	int ground;
	public boolean inAir=true;
	boolean downStay=false;
	public boolean collisionUp=false,collisionDown=false,collisionLeft=false,collisionRight=false;
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
	public void drawPoints(Graphics g) {
		try {
			int entityLeftCol=hitbox.x;
			int entityRightCol=hitbox.x+hitbox.width;
			int entityTopRow=hitbox.y;
			int entityBottomRow=hitbox.y+hitbox.height;
			for (int i =entityLeftCol ; i < entityRightCol+1; i+=48) {
				for (int j = entityTopRow; j < entityBottomRow+1; j+=48) {
					g.setColor(Color.RED);
					g.drawRect(i-5,j-5,10,10);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateHitbox() {
		hitbox.y=y+96;
		if(inAir)
			hitbox.y=y+48;

	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}
