package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX, screenY;
	int hasKey = 0; //Number of Keys or Rewards collected by the player
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width=32;
		solidArea.height = 32;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;;
		speed =4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyH.upPressed == Boolean.TRUE || keyH.downPressed == Boolean.TRUE || keyH.leftPressed == Boolean.TRUE || keyH.rightPressed == Boolean.TRUE) {
			
			if(keyH.upPressed == Boolean.TRUE) {
				direction = "up";
			}else if(keyH.downPressed == Boolean.TRUE) {
				direction = "down";
			}else if(keyH.leftPressed == Boolean.TRUE) {
				direction = "left";
			}else if(keyH.rightPressed == Boolean.TRUE){
				direction = "right";
			}
			
			//Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			//Check Object Collision
			int objIndex =  gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//If collision is false, player can move
			if (collisionOn == false){
				switch (direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}

			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum ==1) {
					spriteNum =2;
				}else if(spriteNum ==2) {
					spriteNum=1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void pickUpObject(int index) {
		
		if(index != 999) {
			String objectName = gp.obj[index].name;
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.obj[index]= null;
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[index] = null;
					hasKey--;
				}
				break;
			case "Boots":
				//TODO Not needed please remove later
				speed += 1;
				gp.obj[index] = null;
				break;
			}
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum ==1) {
				image = up1;
			}else {
				image = up2;
			}			
			break;
		case "down":
			if(spriteNum ==1) {
				image = down1;
			}else {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum ==1) {
				image = left1;
			}else {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum ==1) {
				image = right1;
			}else {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);
	}
}
