package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize=32;
	final int scale = 3;
	public int tileSize= originalTileSize*scale;
	
	final int maxScreenCol=12;
	final int maxScreenRow=9;
	final int screenWidth = tileSize*maxScreenCol;
	final int ScreenHeight =tileSize* maxScreenRow;
	
	int FPS = 60;
	
	KeyInput Kinput=new KeyInput();
	Thread gameThread;
	Player player = new Player(this,Kinput);
	
	int playerX=100;
	int playerY=100;
	int playerSpeed=5;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth,ScreenHeight));
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
		player.draw(g2);
		g2.dispose();
	}

}
