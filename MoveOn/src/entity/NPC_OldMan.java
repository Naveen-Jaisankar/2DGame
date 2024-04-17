package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
	public boolean isInteracted = Boolean.FALSE;
	private static final int DESTINATION_X = 23;
	private static final int DESTINATION_Y = 42;

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	public void setDialogue(){
		dialogues[0] = "Press F to Enter the bus";
	}
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter ==120) {
			Random random = new Random();
		int i = random.nextInt(100)+1;
		if(i<=25) {
			direction = "up";		
			}
		if(i>25 && i<=50) {
			direction = "down";		
			}
		if(i>50 && i<=75) {
			direction = "left";		
			}
		if(i>75 && i<=100) {
			direction = "up";		
			}
		actionLockCounter =0;
		 }
		
		
	}      
	
	public void isInteracted() {
		openDialogBox();
	}
	
	public void openDialogBox(){
			gp.gameState = gp.dialougeState;
			super.openDialogBox();
			
	}
	
	public void update() {
		if(gp.keyHandler.fPressed || isInteracted) {
			if(gp.keyHandler.fPressed) {
				gp.keyHandler.fPressed=Boolean.FALSE;
				isInteracted = Boolean.TRUE;
				gp.isPlayerInContactWithVehicle = Boolean.TRUE;
			}
			if(worldY<=DESTINATION_Y*gp.tileSize) {
				worldY += speed;
				gp.player.worldY += speed;
			}else if(worldY >= DESTINATION_Y*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
			
		}
	}
	
	
	
	
	

}
