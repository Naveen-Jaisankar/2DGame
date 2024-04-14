package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	public boolean messageOn = Boolean.FALSE;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = Boolean.FALSE;
	public String currentDialogue ="";
	public int commandNum = 0;
	public int titleScreenState = 0;
	

	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try{
			InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/PurisaBold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch(FontFormatException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		

	}
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	
	public void draw(Graphics2D g2) {
		this.g2 =g2;
		g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		// Titlestate
		if(gp.gameState == gp.titleState){
			drawTitleScreen();
		}
		
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
	public void drawTitleScreen(){
		if(titleScreenState ==0){
			// title name
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "MoveOn Adventures";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*3;
		// Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);

		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		// Image
		x = gp.screenWidth/2 - ((gp.tileSize*2)/2);
		y+= gp.tileSize*2;
		g2.drawImage(gp.player.down1, x,y,gp.tileSize*2,gp.tileSize*2,null);
		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "New Game";
		x = getXForCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum==0){
			g2.drawString(">", x-gp.tileSize, y);
		}

		text = "Load Game";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum==1){
			g2.drawString(">", x-gp.tileSize, y);
		}

		text = "Quit Game";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum==2){
			g2.drawString(">", x-gp.tileSize, y);
		}
		}
		else if(titleScreenState ==1){
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));

			String text = "Select your class!";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);

			text="Fighter!";
			x = getXForCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0){
				g2.drawString(">", x-gp.tileSize, y);
			}

			text="Thief!";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1){
				g2.drawString(">", x-gp.tileSize, y);
			}

			text="Sorcerer!";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2){
				g2.drawString(">", x-gp.tileSize, y);
			}

			text="Back";
			x = getXForCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3){
				g2.drawString(">", x-gp.tileSize, y);
			}


		}
	}
	public void drawDialogueScreen(){
		// window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2; 
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
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
