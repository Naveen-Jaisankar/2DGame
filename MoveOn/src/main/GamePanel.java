 package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize = 16;
	final int scale =3;
	
	public final int tileSize = originalTileSize * scale; // 48 * 48
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxMap = 10;
	public int currentMap = 0;

	public boolean fullScreenOn = false;
	
	//Game assets
	TileManager tileM = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Sound music = new Sound();
	Sound soundEffects = new Sound();
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	
	
	
	
	//Movable and Immovable Objects
	public Player player = new Player(this,keyHandler);
	public Entity[][] obj = new Entity[maxMap][20];
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][20];
	public InteractiveTile iTile [][] = new InteractiveTile[maxMap][50];
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	
	static final int FPS = 60;
	
	
	//GAME STATE
	public int gameState;
	// Implement enum for this constants later, for readability
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState =2;
	public final int dialougeState =3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame(){
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
//		playMusic(0);
//		stopMusic();
		gameState = titleState;
	}

	public void retry(){
		player.restoreLifeAndMana();
		player.setDefaultPositions();
		aSetter.setMonster();
		aSetter.setNPC();

	}
	public void restart(){
		player.setDefaultValues();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();

		
	}
	
	public void startGamethread() {
		gameThread = new Thread(this);
		gameThread.start();
	}




	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; // draw 60 times per second
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer =0;
		long drawCount = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				// System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
		}
	}
	
	public void update() {
		
		if(gameState == playState) {
//			player
			player.update();
//			NPC
			for(int i=0;i<npc[1].length;i++) {
				if(npc[currentMap][i]!= null) {
					npc[currentMap][i].update();
				}
			}
		}
		// monster
		for(int i=0;i<monster[1].length;i++) {
			if(monster[currentMap][i]!= null) {
				if(monster[currentMap][i].alive == true && monster[currentMap][i].dying ==false){monster[currentMap][i].update();}
				if(monster[currentMap][i].alive == false){
					monster[currentMap][i].checkDrop();
					monster[i]= null;
				}
				
			}
		}
		// projectile
		for(int i=0;i<projectileList.size();i++) {
			if(projectileList.get(i)!= null) {
				if(projectileList.get(i).alive == true ){projectileList.get(i).update();}
				if(projectileList.get(i).alive == false){ projectileList.remove(i);}
				
			}
		}
		
		//Particle
		for(int i=0;i<particleList.size();i++) {
			if(particleList.get(i)!= null) {
				if(particleList.get(i).alive == true ){particleList.get(i).update();}
				if(particleList.get(i).alive == false){ particleList.remove(i);}
				
			}
		}
		
		for(int i =0;i<iTile[1].length;i++) {
			if(iTile[currentMap][i]!=null) {
				iTile[currentMap][i].update();
			}
		}
	
		if(gameState == pauseState) {
			
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//For debugging purpose
		long drawStart = 0;
		if(keyHandler.showDebugText) {
			drawStart = System.nanoTime();
		}
		// Title screen
		if(gameState == titleState){
			ui.draw(g2);
		}
		else {

			//Tile
		tileM.draw(g2);
		
		for(int i =0;i<iTile[1].length;i++) {
			if(iTile[currentMap][i] != null) {
				iTile[currentMap][i].draw(g2);
			}
		}
		
		// add all entities(player,npc,object) to the arraylist
		entityList.add(player);
		// npc
		for(int i=0; i<npc[1].length;i++){
			if(npc[currentMap][i]!=null){
				entityList.add(npc[currentMap][i]);
			}
		}
		// obj
		for(int i=0; i<obj[1].length;i++){
			if(obj[currentMap][i]!=null){
				entityList.add(obj[currentMap][i]);
			}
		}
		// monster
		for(int i=0; i<monster[1].length;i++){
			if(monster[currentMap][i]!=null){
				entityList.add(monster[currentMap][i]);
			}
		}
		// projectile
		for(int i=0; i<projectileList.size();i++){
			if(projectileList.get(i)!=null){
				entityList.add(projectileList.get(i));
			}
		}
		
		//Particle
		for(int i=0; i<particleList.size();i++){
			if(particleList.get(i)!=null){
				entityList.add(particleList.get(i));
			}
		}
		
		// Sort entities based on worldY
		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity e1, Entity e2) {
				int result = Integer.compare(e1.worldY, e2.worldY);
				return result;
			}
			
		});

		// draw

		for(int i=0;i<entityList.size();i++){
			entityList.get(i).draw(g2);
		}

		// once drawn empty the entitylist
		entityList.clear();
		// UI
		ui.draw(g2);
		}
		
		
		// debug
		if(keyHandler.showDebugText) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setFont(new Font("Arial",Font.PLAIN,20));
			g2.setColor(Color.white);
			
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX" + player.worldX, x, y); y+=lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y);y+=lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y);y+=lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y);y+=lineHeight;
			g2.drawString("Draw Time:" + passed, x, y);
		}
		
		
		g2.dispose();
		
	}
	
	public void playMusic(int musicFileNum) {
		music.setFile(musicFileNum);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	//playSE
	public void playSoundEffect(int musicFileNum) {
		soundEffects.setFile(musicFileNum);
		soundEffects.play();
	}

}
