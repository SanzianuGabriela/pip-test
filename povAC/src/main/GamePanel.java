package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//Stabileste dimensiunea unui "pixel" de pe mapa
	final int originalTileSize=8;
	final int scale = 2;
	public int tileSize= originalTileSize*scale;
	
	
	//Dimensionile Ferestrei Jocului 
	public int maxScreenCol=48;
	public int maxScreenRow=36;
	public final int ScreenWidth = tileSize*maxScreenCol;
	public int ScreenHeight =tileSize* maxScreenRow;
	
	//WORLD
	public int screenX=(int) (ScreenWidth/2-(6*tileSize));
	public int maxWorldCol=192;
	public int maxWorldRow=36;
	public final int WorldWidth = tileSize*maxWorldCol;
	public int WorldHeight =tileSize* maxWorldRow;
	public int lastWorldX=WorldWidth-ScreenWidth;
	
	int FPS = 60;
	
	TileManager tileM= new TileManager(this);
	KeyInput Kinput=new KeyInput();
	Thread gameThread;
	public Collision checkC=new Collision(this);
	
	//marimile personajului
	int playerX=(int) (screenX+tileSize*4);
	int playerY=2*tileSize;
	int playerSpeed=5;
	
	
	public Player player = new Player(this,Kinput,playerX,playerY,(int) (tileSize*4),(int)(tileSize*8));
	
	
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(Kinput);
		this.setFocusable(true);
	
	}
	public void startGameThread(){//creatrea unui thread in care se va realiza joaca 
		gameThread=new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() { //realizraza updatarea la 60FPS
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
	public void update(){ //realizeaza updatul
		player.update();
	}
	public void paintComponent(Graphics g){ //deseneaza totul in Window
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g; 
		tileM.draw(g2);  //deseneaza obstacolele "Mapa"
		player.draw(g2); //deseneaza jucatorul
		g2.dispose();
	}

}
