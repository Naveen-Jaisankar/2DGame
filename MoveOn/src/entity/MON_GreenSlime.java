package entity;
import main.GamePanel;

import java.util.Random;

import entity.Entity;

public class MON_GreenSlime extends Entity {
    GamePanel gp;
    
    public MON_GreenSlime(GamePanel gp){

        super(gp);
        this.gp = gp;
        name = "Green Slime";
        speed = 1;
        maxLife =4;
        life = maxLife;
        type = 2;
        attack = 5;
        defense = 0;
        exp = 2;
        
        solidArea.x = 3;
        solidArea.y = 10;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        up1 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);

    }
    // public void setAction(){
    //     actionLockCounter++;
	// 	if(actionLockCounter ==120) {
	// 		Random random = new Random();
    //         int i = random.nextInt(100)+1;
    //         if(i<=25) {
    //             direction = "up";		
    //             }
    //         if(i>25 && i<=50) {
    //             direction = "down";		
    //             }
    //         if(i>50 && i<=75) {
    //             direction = "left";		
    //             }
    //         if(i>75 && i<=100) {
    //             direction = "up";		
    //             }
    //         actionLockCounter =0;
    //         }

    // }

    public void setAction() {
		int xDistance;
		int yDistance;
		int Distance;
		
		for(int i = 0; i < gp.monster.length; i++) {
			if(gp.monster[i] != null) {
				xDistance = Math.abs(gp.player.worldX - gp.monster[i].worldX);
				yDistance = Math.abs(gp.player.worldY - gp.monster[i].worldY);
				Distance = Math.max(xDistance, yDistance);
				if(gp.monster[i].alive == true && gp.monster[i].dying ==false) {
					if(Distance < 4*gp.tileSize) {
						if(gp.player.worldX > gp.monster[i].worldX && gp.player.worldY > gp.monster[i].worldY) {
							if(changeDirection1 < 30) {
								gp.monster[i].direction = "right";
							}
							else if(changeDirection1 >=45 && changeDirection1 < 90) {
								gp.monster[i].direction = "down";
							}
							else if(changeDirection1 > 90){
								changeDirection1 = 0;
							}
							changeDirection1++;
						}
						else if(gp.player.worldX > gp.monster[i].worldX && gp.player.worldY < gp.monster[i].worldY) {
							if(changeDirection2 < 45) {
								gp.monster[i].direction = "right";
							}
							else if(changeDirection2 >=45 && changeDirection2 < 90) {
								gp.monster[i].direction = "up";
							}
							else if(changeDirection2 > 90){
								changeDirection2 = 0;
							}
							changeDirection2++;
						}
						else if(gp.player.worldX < gp.monster[i].worldX && gp.player.worldY > gp.monster[i].worldY) {
							if(changeDirection3 < 45) {
								gp.monster[i].direction = "left";
							}
							else if(changeDirection3 >=45 && changeDirection3 < 90) {
								gp.monster[i].direction = "down";
							}
							else if(changeDirection3 > 90){
								changeDirection3 = 0;
							}
							changeDirection3++;
						}
						else if(gp.player.worldX < gp.monster[i].worldX && gp.player.worldY < gp.monster[i].worldY) {
							if(changeDirection4 < 45) {
								gp.monster[i].direction = "left";
							}
							else if(changeDirection4 >=45 && changeDirection4 < 90) {
								gp.monster[i].direction = "up";
							}
							else if(changeDirection4 > 90){
								changeDirection4 = 0;
							}
							changeDirection4++;
						}
						else if(gp.player.worldX == gp.monster[i].worldX && gp.player.worldY < gp.monster[i].worldY) {
							gp.monster[i].direction = "up";
						}
						else if(gp.player.worldX == gp.monster[i].worldX && gp.player.worldY > gp.monster[i].worldY) {
							gp.monster[i].direction = "down";
						}
						else if(gp.player.worldX < gp.monster[i].worldX && gp.player.worldY == gp.monster[i].worldY) {
							gp.monster[i].direction = "left";
						}
						else if(gp.player.worldX > gp.monster[i].worldX && gp.player.worldY == gp.monster[i].worldY) {
							gp.monster[i].direction = "right";
						}
						
					}
					else {
						actionLockCounter++;
						
						if(actionLockCounter == 600) {
						
						Random random = new Random();
						int a = random.nextInt(100)+1; //picks up a number from 1 to 100
						
						if(a <= 25) {
							direction = "up";
						}
						else if(a > 25 && a <= 50) {
							direction = "left";
						}
						else if(a > 50 && a <= 75) {
							direction = "down";
						}
						else if(a > 75 && a <= 100) {
							direction = "right";
						}
						actionLockCounter = 0;
						}
					}
				}				
			}
		}	
	}

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
