package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize=32;
	final int scale = 3;
	public int tileSize= originalTileSize*scale;
	
	public int maxScreenCol=12;
	public int maxScreenRow=9;
	public final int ScreenWidth = tileSize*maxScreenCol;
	public int ScreenHeight =tileSize* maxScreenRow;
	
	//WORLD
	public int screenX=(int) (ScreenWidth/2-(1.5*tileSize));
	public int maxWorldCol=24;
	public int maxWorldRow=9;
	public final int WorldWidth = tileSize*maxWorldCol;
	public int WorldHeight =tileSize* maxWorldRow;
	public int lastWorldX=WorldWidth-ScreenWidth;
	
	int FPS = 60;
	
	TileManager tileM= new TileManager(this);
	KeyInput Kinput=new KeyInput();
	Thread gameThread;
	int playerX=80;
	int playerY=80;
	int playerSpeed=5;
	
	
	public Player player = new Player(this,Kinput,screenX,playerY,tileSize*2,(int)(tileSize*3));
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(Kinput);
		this.setFocusable(true);
	
	}
	public void startGameThread(){
		gameThread=new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		while(gameThread != null){
			double drawInterval =1000000000/FPS;
			double newDrawTime = System.nanoTime() +drawInterval;
			update();
			repaint();
			try {
				double remainingTime = newDrawTime -System.nanoTime();
				remainingTime=remainingTime/1000000;
				if(remainingTime<0){
					remainingTime=0;
				}
				Thread.sleep((long)remainingTime);
				newDrawTime+=drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void update(){
		player.update();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}

}
