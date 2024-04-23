package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;

import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
	public boolean messageOn = Boolean.FALSE;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = Boolean.FALSE;
	public String currentDialogue ="";
	public int commandNum = 0;
	public int titleScreenState = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	

	
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
		
		//Create Heart Object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		

	}
	
	
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
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
			drawPlayerLife();
			drawMessage();
		}
		// Pausestate
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// dialoguestate
		if(gp.gameState == gp.dialougeState){
			drawPlayerLife();
			drawDialogueScreen();
		}
		//CHARACTER STATE
		if(gp.gameState==gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		// options state
		if(gp.gameState==gp.optionsState) {
			drawOptionsScreen();
		}
		
	}
	
	
	
	public void drawPlayerLife() {
		
//		gp.player.life =2;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//DRAW CURRENT LIFE
		while(i<gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+=gp.tileSize;
		}

		// draw maxmana
		x = gp.tileSize/2-5;
		y=(int)(gp.tileSize*1.5);
		i=0;
		while(i<gp.player.maxMana){
			g2.drawImage(crystal_blank, x, y,null);
			i++;
			x+=35;
		}
		// draw player mana
		x = gp.tileSize/2-5;
		y=(int)(gp.tileSize*1.5);
		i=0;
		while(i<gp.player.mana){
			g2.drawImage(crystal_full, x, y,null);
			i++;
			x+=35;
		}

		
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		
		for(int i=0;i<message.size();i++) {
			if(message.get(i)!=null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
;				}
			}
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
	
	public void drawCharacterScreen() {
		//Create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize *10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35; //Same as font size 32F
		
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life",textX,textY);
		textY += lineHeight;
		g2.drawString("Mana",textX,textY);
		textY += lineHeight;
		g2.drawString("Strength",textX,textY);
		textY += lineHeight;
		g2.drawString("Dexterity",textX,textY);
		textY += lineHeight;
		g2.drawString("Attack",textX,textY);
		textY += lineHeight;
		g2.drawString("Defense",textX,textY);
		textY += lineHeight;
		g2.drawString("Exp",textX,textY);
		textY += lineHeight;
		g2.drawString("Next Level",textX,textY);
		textY += lineHeight;
		g2.drawString("Coin",textX,textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon",textX,textY);
		textY += lineHeight + 15;
		g2.drawString("Shield",textX,textY);
		textY += lineHeight;
		
		//VALUES
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + gp.tileSize;
		
		String value;
		value = String.valueOf(gp.player.level);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		
		value = String.valueOf(gp.player.attack);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		
		value = String.valueOf(gp.player.defense);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		
		value = String.valueOf(gp.player.exp);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXForAlignToRightText(value,tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1,tailX - gp.tileSize,textY-24,null);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentShield.down1,tailX - gp.tileSize,textY-14,null);
	}
	
	public void drawInventory() {
		
		//Frame
		int frameX = gp.tileSize*9;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		//slot
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize +3;
		
		//Draw Player's Item
		for(int i =0;i<gp.player.inventory.size();i++) {
			if(gp.player.inventory.get(i)==gp.player.currentWeapon || gp.player.inventory.get(i)==gp.player.currentShield){
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);
			slotX+=gp.tileSize;
			
			if(i ==4 || i==9 || i==14) {
				slotX = slotXStart;
				slotY+=slotSize;
			}
		}
		
		//Cursor
		int cursorX = slotXStart + (slotSize * slotCol);
		int cursorY = slotYStart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		
		//Draw Cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		//Description window
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		
		
		
		//Draw Description Text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			for(String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY +=32;
			}
			
		}
		
		
		
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}

	public void drawOptionsScreen(){
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		// sub window
		int frameX = gp.tileSize*4 - gp.tileSize/2;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*9- gp.tileSize/2;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
			case 0:
			options_top(frameX,frameY);
				break;
			case 1: options_fullScreenNotification(frameX,frameY); break;
			case 2: options_control(frameX,frameY); break;
			case 3: options_endGameConfirmation(frameX,frameY); break;
			default: break;
		}

		gp.keyHandler.enterPressed = false;
	}

	public void options_top(int frameX, int frameY){
		int textX;
		int textY;

		// title
		String text = "Options";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		// full screen on/off
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				if (gp.fullScreenOn == false){
					gp.fullScreenOn = true;
				}else if (gp.fullScreenOn == true){
					gp.fullScreenOn = false;
				}
				subState =1;
				commandNum=0;
			}
		}

		// Music
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1){
			g2.drawString(">", textX-25, textY);
		}

		// SE
		textY += gp.tileSize;
		g2.drawString("Sound Effects", textX, textY);
		if(commandNum == 2){
			g2.drawString(">", textX-25, textY);
		}

		// Control
		textY += gp.tileSize;
		g2.drawString("Controls", textX, textY);
		if(commandNum == 3){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				subState =2;
				commandNum =0;
			}
		}

		// End game
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed==true){
				subState = 3;
				commandNum =0;
			}
		}

		// back
		textX = getXForCenteredText("back");
		textY += gp.tileSize*2;
		g2.drawString("back", textX, textY);
		if(commandNum == 5){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				gp.gameState = gp.playState;
				commandNum=0;
			}
		}

		// full screen check box
		textX = frameX + (int)(gp.tileSize*5);
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true){
			g2.fillRect(textX, textY, 24, 24);
		}

		// music volume slider
		textY+=gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24*gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		// sound effects slider
		textY+=gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24*gp.soundEffects.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);



	}

	public void options_fullScreenNotification(int frameX, int frameY){
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;

		currentDialogue = "The change will take \neffect after restarting \nthe game";

		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, textX, textY);
			textY+=40;

		}
		// back
		String text = "Back";
		textX = getXForCenteredText(text);
		textY= frameY+gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				subState =0;

			}
		}


	}

	public void options_control(int frameX, int frameY){
		int textX;
		int textY;
		// title
		String text = "Controls";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY+= gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY); textY+= gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY); textY+= gp.tileSize;
		g2.drawString("Character Search", textX, textY); textY+= gp.tileSize;
		g2.drawString("Pause", textX, textY); textY+= gp.tileSize;
		g2.drawString("Options", textX, textY); textY+= gp.tileSize;

		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;

		g2.drawString("↑←↓→", textX, textY); textY+= gp.tileSize;
		g2.drawString("Enter", textX, textY); textY+= gp.tileSize;
		g2.drawString("F", textX, textY); textY+= gp.tileSize;
		g2.drawString("C", textX, textY); textY+= gp.tileSize;
		g2.drawString("P", textX, textY); textY+= gp.tileSize;
		g2.drawString("ESC", textX, textY); textY+= gp.tileSize;







		// back

		text = "Back";
		textX = getXForCenteredText(text);
		textY= frameY+gp.tileSize*9;
		g2.drawString(text, textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				subState =0;
				commandNum =3;

			}
		}

	}

	public void options_endGameConfirmation(int frameX, int frameY){
		int textX;
		int textY;
		// title
		String text = "End Game";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*3;

		currentDialogue ="Quit the game?";

		g2.drawString(currentDialogue, textX, textY);

		// Yes option
		text = "Yes";
		textX = getXForCenteredText(text);
		textY+= gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				subState =0;
				gp.gameState = gp.titleState;
			}
		}
		// No option
		text = "No";
		textX = getXForCenteredText(text);
		textY+= gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1){
			g2.drawString(">", textX-25, textY);
			if(gp.keyHandler.enterPressed == true){
				subState =0;
				commandNum = 4;
			}
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
	
	public int getXForAlignToRightText(String text,int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
