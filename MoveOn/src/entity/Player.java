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

		attackArea.width = 36;
		attackArea.height = 36;


		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
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
		up1 = setup("/player/boy_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/player/boy_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/player/boy_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/player/boy_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/player/boy_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/player/boy_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/player/boy_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/player/boy_right_2",gp.tileSize,gp.tileSize);
	}

	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup("/player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
	}
	
		
	public void update() {
		if(attacking){
			attacking();

		}else if(keyH.upPressed == Boolean.TRUE || keyH.downPressed == Boolean.TRUE || keyH.leftPressed == Boolean.TRUE || keyH.rightPressed == Boolean.TRUE
		|| keyH.enterPressed == true) {
			
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
			
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			gp.eHandler.checkEvent();
			
			
			//If collision is false, player can move
			if (collisionOn == false && keyH.enterPressed == false){
				switch (direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			gp.keyHandler.enterPressed = false;

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

	public void attacking(){
		spriteCounter++;
		if(spriteCounter<=5){
			spriteNum = 1;
		}
		if(spriteCounter>5 && spriteCounter<=25){
			spriteNum=2;

			// save current worldx,worldy,solidarea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			// adjust player's worldx,worldy for attackarea
			switch (direction) {
				case "up": worldY -= attackArea.height; break;
				case "down": worldY += attackArea.height; break;
				case "left": worldX -= attackArea.width; break;
				case "right": worldX += attackArea.width; break;
			
				default:
					break;
			}
			// attackarea is solidarea now
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// check monster collision with updated solidarea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);

			// reset solidarea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter>25){
			spriteNum=1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void interactNPC(int index) {
		if(gp.keyHandler.enterPressed == true){
			if(index!=999) {
				gp.gameState = gp.dialougeState;
				gp.npc[index].speak();

			}
			else{
				attacking = true;
				
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

	public void damageMonster(int index){
		if(index!=999){
			if(gp.monster[index].invincible == false){
				gp.monster[index].life -=1;
				gp.monster[index].invincible = true;

				if(gp.monster[index].life<=0){
					gp.monster[index] = null;
				}
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
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY =screenY;
		
		switch(direction) {
		case "up":
		if(attacking == true){
			tempScreenY = screenY - gp.tileSize;
			if(spriteNum ==1) {image = attackUp1; }
			if(spriteNum == 2) { image = attackUp2;}
		}
		if(attacking==false){
			if(spriteNum ==1) {image = up1; }
			if(spriteNum ==2 ) { image = up2;}
			}
					
			break;
		case "down":
		if(attacking == true){
			if(spriteNum ==1) {image = attackDown1; }
			if(spriteNum == 2) { image = attackDown2;}
		}
		if(attacking==false){
			if(spriteNum ==1) {image = down1; }
			if(spriteNum ==2 ) { image = down2;}
			}
			
			break;
		case "left":
		if(attacking == true){
			tempScreenX = screenX - gp.tileSize;
			if(spriteNum ==1) {image = attackLeft1; }
			if(spriteNum == 2) { image = attackLeft2;}
		}
		if(attacking==false){
			if(spriteNum ==1) {image = left1; }
			if(spriteNum ==2 ) { image = left2;}
			}
			
			break;
		case "right":
		if(attacking == true){
			if(spriteNum ==1) {image = attackRight1; }
			if(spriteNum == 2) { image = attackRight2;}
		}
		if(attacking==false){
			if(spriteNum ==1) {image = right1; }
			if(spriteNum ==2 ) { image = right2;}
			}
			
			break;
		}
		if(invincible){
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// debug
		// g2.setFont(new Font("Arial",Font.PLAIN,26));
		// g2.setColor(Color.white);
		// g2.drawString("Invincible:"+invincibleCounter, 10, 400);
		;
	}
}
