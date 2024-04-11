package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	GamePanel gp;
	Font arial_40,arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = Boolean.FALSE;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = Boolean.FALSE;
	
	double playTime; 
	DecimalFormat dformat = new DecimalFormat("#0.00");
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 =  new Font("Arial",Font.PLAIN,40);
		arial_80B =  new Font("Arial",Font.BOLD,80);
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
	}
	
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
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
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
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
}
