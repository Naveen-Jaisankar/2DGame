package entity;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity{
	
	KeyHandler keyH;
	public final int screenX, screenY;
	public boolean attackCancelled = Boolean.FALSE;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize =2;
	
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
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
		speed =4;
		direction = "down";
		
		//PLAYER STATUS
		level =1;
		maxLife = 6;
		life = maxLife;
		strength = 1;//strength >>>, damage >>>
		dexterity =1;// dexterity >>>, damage <<<
		exp =0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		attack = getAttack();
		defense = getDefense();
	}
	
	public void setItems() {
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
	}
	
	public int getAttack() {
		// System.out.println(currentWeapon.name);
		attackArea= currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		// System.out.println(currentShield.name);
		return defense = dexterity * currentShield.defenseValue;
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
		if(currentWeapon.type == type_sword){
			attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup("/player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
		}
		if(currentWeapon.type == type_axe){
			attackUp1 = setup("/player/boy_axe_up_1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/player/boy_axe_up_2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup("/player/boy_axe_down_1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/player/boy_axe_down_2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup("/player/boy_axe_left_1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/player/boy_axe_left_2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup("/player/boy_axe_right_1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/player/boy_axe_right_2",gp.tileSize*2,gp.tileSize);

		}
		
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
			
			if(keyH.enterPressed == Boolean.TRUE && attackCancelled == Boolean.FALSE) {
				gp.playSoundEffect(7);
				attacking = Boolean.TRUE;
				spriteCounter = 0;
			}
			
			attackCancelled = Boolean.FALSE;
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
				attackCancelled = Boolean.TRUE;
				gp.gameState = gp.dialougeState;
				gp.npc[index].speak();

			}
				
		}
		
	}
	public void contactMonster(int index){
	if(index!=999){
		if(invincible == false && gp.monster[index].alive == true && gp.monster[index].dying == false){
			gp.playSoundEffect(6);
			
			int damage = gp.monster[index].attack - defense;
			
			if(damage<0) {
				damage =0;
			}
			
			life -=damage;
			invincible = true;

		}
		
	}
	}

	public void damageMonster(int index){
		if(index!=999){
			if(gp.monster[index].invincible == false){
				gp.playSoundEffect(5);
				
				int damage = attack - gp.monster[index].defense;
				
				if(damage<0) {
					damage =0;
				}
				
				gp.monster[index].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[index].invincible = true;
				
				gp.monster[index].damageReaction();

				if(gp.monster[index].life<=0){
					gp.monster[index].dying = true;
					gp.ui.addMessage("Killed the " + gp.monster[index].name + "!");
					gp.ui.addMessage("Exp " + gp.monster[index].exp + "!");
					exp += gp.monster[index].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void checkLevelUp() {
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSoundEffect(8);
			gp.gameState = gp.dialougeState;
			gp.ui.currentDialogue = "You are in level " + level + " now!\n"
					+"You feel Stronger!!!";
			
		}
	}
	
	public void pickUpObject(int index) {
		
		if(index != 999) {
			String text;
			if(inventory.size()!=maxInventorySize){
				inventory.add(gp.obj[index]);
				gp.playSoundEffect(1);
				text="Got a " + gp.obj[index].name + "!";
			}else{
				text="You cannot carry anymore!";
			}
			gp.ui.addMessage(text);
			gp.obj[index]= null;
			
		}
		
	}

	public void selectItem(){
		int itemIndex = gp.ui.getItemIndexOnSlot();
		if(itemIndex < inventory.size()){
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type_axe || selectedItem.type == type_sword){
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield){
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable){
				selectedItem.use(this);
				inventory.remove(itemIndex);

			}
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
