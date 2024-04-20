package entity;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
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
		worldY = gp.tileSize*21;
		speed =4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
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
			
//			check NPC COllision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			gp.eHandler.checkEvent();
			gp.keyHandler.enterPressed = false;
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
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
		// outside key if statement
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>120){
				invincible = false;
				invincibleCounter=0;
			}
		}
		
	}
	public void interactNPC(int index) {
		if(index!=999) {
			if(gp.keyHandler.enterPressed == true){
				gp.gameState = gp.dialougeState;
				gp.npc[index].speak();
			}
			
		}
	}
	public void contactMonster(int index){
	if(index!=999){
		if(invincible == false){
			life -=1;
			invincible = true;

		}
		
	}
	}
	
	public void pickUpObject(int index) {
		
		if(index != 999) {
//			String objectName = gp.obj[index].name;
//			objectName.length();
//			switch(objectName) {
//			case "Key":
//				gp.playSoundEffect(1);
//				hasKey++;
//				gp.obj[index]= null;
//				gp.ui.showMessage("You got a key!");
//				break;
//			case "Door":
//				gp.playSoundEffect(3);
//				if(hasKey > 0) {
//					gp.obj[index] = null;
//					hasKey--;
//					gp.ui.showMessage("You opened the door!");
//				}else {
//					gp.ui.showMessage("You need a key");
//				}
//				break;
//			case "Boots":
//				// Not needed please remove later
//				gp.ui.showMessage("Speed up");
//				gp.playSoundEffect(2);
//				speed += 1;
//				gp.obj[index] = null;
//				break;
//			case "Chest":
//				gp.ui.gameFinished = Boolean.TRUE;
////				gp.stopMusic();
//				gp.playSoundEffect(4);
//				break;
//			}
			
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
		if(invincible){
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			
		}
		g2.drawImage(image, screenX, screenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// debug
		// g2.setFont(new Font("Arial",Font.PLAIN,26));
		// g2.setColor(Color.white);
		// g2.drawString("Invincible:"+invincibleCounter, 10, 400);
		;
	}
}
