package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_80B;
	public boolean messageOn = Boolean.FALSE;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = Boolean.FALSE;
	public String currentDialogue ="";
	

	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 =  new Font("Arial",Font.PLAIN,40);
		arial_80B =  new Font("Arial",Font.BOLD,80);

	}
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	/**
	 * This draw method is the v1.
	public void draw(Graphics2D g2) {
		
		if(gameFinished) {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textLength;
			int gameCenterX;
			int gameCenterY;

			//TODO Implement the following line of code in a function
			text = "You found the treasure";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			gameCenterX = gp.screenWidth/2 - textLength/2; // aligning the text to the center of the screen 
			gameCenterY = gp.screenHeight/2 - (gp.tileSize*3);
			
			g2.drawString(text, gameCenterX, gameCenterY);
			
			
			text = "Your Time is :" + dformat.format(playTime) + "!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			gameCenterX = gp.screenWidth/2 - textLength/2; // aligning the text to the center of the screen 
			gameCenterY = gp.screenHeight/2 + (gp.tileSize*4);
			
			g2.drawString(text, gameCenterX, gameCenterY);
			
			
			g2.setFont(arial_80B);
			g2.setColor(Color.white);
			text = "Congratulations!!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			gameCenterX = gp.screenWidth/2 - textLength/2; // aligning the text to the center of the screen 
			gameCenterY = gp.screenHeight/2 + (gp.tileSize*3);
			
			g2.drawString(text, gameCenterX, gameCenterY);
			
			//TODO Move this to a sepearate function in gamePanel as well, and come up with some abstraction to make it more secure as it stops the game thread.
			gp.gameThread = null;
			
			
		}else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
//			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
			g2.drawString("x "+ gp.player.hasKey,74, 65);
			
			//Time
			playTime += (double)1/60;
			g2.drawString("Time:"+ dformat.format(playTime), gp.tileSize*11, 65);
			
			if(messageOn) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
	**/
	
	public void draw(Graphics2D g2) {
		this.g2 =g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// Playstate
		if(gp.gameState == gp.playState) {
			//Later
		}
		// Pausestate
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// dialoguestate
		if(gp.gameState == gp.dialougeState){
			drawDialogueScreen();
		}
	}

	public void drawDialogueScreen(){
		// window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2; 
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x+=gp.tileSize;
		y+=gp.tileSize;
		for(String line : currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y+=40;

		}
		
	}
	public void drawSubWindow(int x,int y,int width,int height){
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);


	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "Paused";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return (gp.screenWidth/2 - length/2);
	
	}
}
