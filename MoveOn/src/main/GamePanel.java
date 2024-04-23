 package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.NonPlayableEntity;
import entity.Player;
import tile.TileManager;

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
	
	
	
	
	//Movable and Immovable Objects
	public Player player = new Player(this,keyHandler);
	public Entity[] obj = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	static final int FPS = 60;
	
	
	//GAME STATE
	public int gameState;
	// Implement enum for this constants later, for readability
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState =2;
	public final int dialougeState =3;
	
	
	//Player
	public boolean isPlayerInContactWithVehicle = Boolean.FALSE;
	
	
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
//		playMusic(0);
//		stopMusic();
		gameState = titleState;
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
<<<<<<< HEAD
//				System.out.println("FPS:" + drawCount);
=======
				// System.out.println("FPS:" + drawCount);
>>>>>>> refs/remotes/origin/dev
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
			for(int i=0;i<npc.length;i++) {
				if(npc[i]!= null) {
					npc[i].update();
				}
			}
		}
		// monster
		for(int i=0;i<monster.length;i++) {
			if(monster[i]!= null) {
				if(monster[i].alive == true && monster[i].dying ==false){monster[i].update();}
				if(monster[i].alive == false){ monster[i]= null;}
				
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
		if(keyHandler.checkDrawtime) {
			drawStart = System.nanoTime();
		}
		// Title screen
		if(gameState == titleState){
			ui.draw(g2);
		}
		else {

			//Tile
		tileM.draw(g2);
		// add all entities(player,npc,object) to the arraylist
		entityList.add(player);
		for(int i=0; i<npc.length;i++){
			if(npc[i]!=null){
				entityList.add(npc[i]);
			}
		}
<<<<<<< HEAD
		//Player
		if(!isPlayerInContactWithVehicle) {
			player.draw(g2);
		}
=======
		for(int i=0; i<obj.length;i++){
			if(obj[i]!=null){
				entityList.add(obj[i]);
			}
		}
		for(int i=0; i<monster.length;i++){
			if(monster[i]!=null){
				entityList.add(monster[i]);
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
>>>>>>> refs/remotes/origin/dev
		// UI
		ui.draw(g2);
		}
		
		
		// debug
		if(keyHandler.checkDrawtime) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time:" + passed, 10, 400);
			System.out.println("Draw Time : " + passed);
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
