package Entity;

import java.awt.image.BufferedImage;

public class Entity {
	public int x,y,speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,stay1,stay2,stay3,stay4,stay5,stay6,stay7,stay8,stay9,stay10;
	public BufferedImage[][] animations;
	public BufferedImage img;
	public String direction,lastDir;
	int gravity,ground;
	boolean inAir=false,downStay=false;
	public int spriteCounter=-5,velosity;
	public int spriteNum=1;
	public int aniTick,aniIndex, aniSpeed =10,nrFrames,stayCount;
	
	
}
