package entity;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Rock;
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
		worldX = gp.tileSize*44;
		worldY = gp.tileSize*15;
		// worldX = gp.tileSize*12;
		// worldY = gp.tileSize*13;
		speed =4;
		direction = "down";
		name = "player";
		
		//PLAYER STATUS
		level =1;
		maxLife = 6;
		life = maxLife;
		maxMana = 10;
		mana = maxMana;
		maxCarbonFootPrints = 300;
		carbonFootPrints = maxCarbonFootPrints;
		ammo = 10;
		strength = 1;//strength >>>, damage >>>
		dexterity =1;// dexterity >>>, damage <<<
		exp =0;
		nextLevelExp = 5;
		coin = 0;
//		currentWeapon = new OBJ_Sword_Normal(gp);
		currentWeapon = new OBJ_Axe(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		// projectile = new OBJ_Rock(gp);
		attack = getAttack();
		defense = getDefense();
	}

	public void setDefaultPositions(){
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
		direction = "down";
	}

	public void restoreLifeAndMana(){
		life = maxLife;
		mana = maxMana;
		carbonFootPrints = maxCarbonFootPrints;
		invincible = false;
	}
	
	public void setItems() {
		inventory.clear();
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
			
			//check vehicle collision
			int vehicleIndex = gp.cChecker.checkEntity(this, gp.vehicle);
			interactVehicle(vehicleIndex);
			
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

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
		if(gp.keyHandler.shotKeyPressed && projectile.alive == false && shotAvailableCounter == 30
		&& projectile.haveResource(this)==true){
			// player co=ord, direction and user
			projectile.set(worldX,worldY,direction,true,this);
			// UPDATE MANA
			projectile.subtractResource(this);
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
			gp.playSoundEffect(10);
		}
		// outside key if statement
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>120){
				invincible = false;
				invincibleCounter=0;
			}
		}

		if(shotAvailableCounter<30){
			shotAvailableCounter++;
		}
		
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life<=0){
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum=-1;
			gp.stopMusic();
			gp.playSoundEffect(12);
		}

		if(carbonFootPrints<=0){
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum=-1;
//			gp.stopMusic();
			gp.playSoundEffect(12);
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
			damageMonster(monsterIndex,attack);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);

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
				gp.npc[gp.currentMap][index].openDialogBox();

			}
				
		}
		
	}


	
	
	public void interactVehicle(int index) {
		if(index!=999) {
			if(gp.keyHandler.qPressed) {
				gp.keyHandler.qPressed = Boolean.FALSE;
			}
			gp.vehicle[gp.currentMap][index].isInteracted();			
		}
	}
	
	
	public void contactMonster(int index){
	if(index!=999){
		if(invincible == false && gp.monster[gp.currentMap][index].alive == true && gp.monster[gp.currentMap][index].dying == false){
			gp.playSoundEffect(6);
			
			int damage = gp.monster[gp.currentMap][index].attack - defense;
			
			if(damage<0) {
				damage =0;
			}
			
			life -=damage;
			invincible = true;

		}
		
	}
	}

	public void damageMonster(int index, int attack){
		if(index!=999){
			if(gp.monster[gp.currentMap][index].invincible == false){
				gp.playSoundEffect(5);
				
				int damage = attack - gp.monster[gp.currentMap][index].defense;
				
				if(damage<0) {
					damage =0;
				}
				
				gp.monster[gp.currentMap][index].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[gp.currentMap][index].invincible = true;
				
				gp.monster[gp.currentMap][index].damageReaction();

				if(gp.monster[gp.currentMap][index].life<=0){
					gp.monster[gp.currentMap][index].dying = true;
					gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][index].name + "!");
					gp.ui.addMessage("Exp " + gp.monster[gp.currentMap][index].exp + "!");
					exp += gp.monster[gp.currentMap][index].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		if(i!=999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = Boolean.TRUE;
			
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life==0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
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
			//PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][index].type == type_pickUpOnly) {
				// System.out.println("Picking : " +  gp.obj[gp.currentMap][index].name);
				gp.obj[gp.currentMap][index].use(this);
				gp.obj[gp.currentMap][index] = null;
			}
			//INVENTORY ITEMS
			else {
				String text;
				if(inventory.size()!=maxInventorySize){
					inventory.add(gp.obj[gp.currentMap][index]);
					gp.playSoundEffect(1);
					text="Got a " + gp.obj[gp.currentMap][index].name + "!";
				}else{
					text="You cannot carry anymore!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][index]= null;
			}
			
			
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
