package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public BufferedImage up1,  up2, down1, down2, left1,left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image,image2,image3;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[20];

	// state
	public int worldX,worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible =false;
	public boolean attacking = false;
	public boolean alive =true;
	public boolean dying = false;

	// counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int dyingCounter =0;
	public int changeDirection1 = 0;
    public int changeDirection2 = 0;
    public int changeDirection3 = 0;
    public int changeDirection4 = 0;

	// character attributes
	public int speed;
    public String name;  
	public int type; 
	//  0- player, 1 - npc, 2-monster
	public int maxLife;
	public int life;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void dyingAnimation(Graphics2D g2){
		dyingCounter++;
		int i=5;
		if(dyingCounter<=i){changeAlpha(g2,0f);}
		if(dyingCounter>i && dyingCounter <=i*2){changeAlpha(g2,0f);}
		if(dyingCounter>i*2 && dyingCounter <=i*3){changeAlpha(g2,0f);}
		if(dyingCounter>i*3 && dyingCounter <=i*4){changeAlpha(g2,0f);}
		if(dyingCounter>i*4 && dyingCounter <=i*5){changeAlpha(g2,0f);}
		if(dyingCounter>i*5 && dyingCounter <=i*6){changeAlpha(g2,0f);}
		if(dyingCounter>i*6 && dyingCounter <=i*7){changeAlpha(g2,0f);}
		if(dyingCounter>i*7 && dyingCounter <=i*8){changeAlpha(g2,0f);}
		if(dyingCounter>i*8){
			dying = false;
			alive = false;
		}

	}
	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
	}
	
	public BufferedImage setup(String imagePath,int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage scaledImage = null;
		
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			scaledImage = uTool.scaleImage(scaledImage, width, height);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	public void setAction() {}
	public void speak(){
		if(dialogues[dialogueIndex]==null){
			dialogueIndex =0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		switch (gp.player.direction) {
			case "up":
				direction="down";
				break;
			case "down":
				direction="up";
				break;
			case "left":
				direction="right";
				break;
			case "right":
				direction="left";
				break;
			default:
				break;
		}
	}
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if(this.type == 2 && contactPlayer == true){
			if(gp.player.invincible == false){
				gp.player.life-=1;
				gp.player.invincible = true;
			}
		}
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

		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>40){
				invincible = false;
				invincibleCounter=0;
			}
		}
	}    
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	
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
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
				
			}
			if(dying == true){ dyingAnimation(g2);}
            g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
		
	}


	
}
