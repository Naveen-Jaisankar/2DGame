package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Luas extends Entity{
	
	public boolean isInteracted = Boolean.FALSE;
	private static final int DESTINATION_X = 23;
	private static final int DESTINATION_Y = 12;

	public NPC_Luas(GamePanel gp) {
		super(gp);
		
		direction = "up";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1 = setup("/npc/merchant_down_1");
		up2 = setup("/npc/merchant_down_1");
		down1 = setup("/npc/merchant_down_1");
		down2 = setup("/npc/merchant_down_1");
		left1 = setup("/npc/merchant_down_1");
		left2 = setup("/npc/merchant_down_1");
		right1 = setup("/npc/merchant_down_1");
		right2 = setup("/npc/merchant_down_1");
	}
	public void setDialogue(){
		dialogues[0] = "Press F to Enter the cycle";
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
		int npcIndex = gp.cChecker.checkEntity(gp.player, this);
		if(npcIndex!=999 & (gp.keyHandler.fPressed || isInteracted)) {
			if(gp.keyHandler.fPressed) {
				gp.keyHandler.fPressed=Boolean.FALSE;
				isInteracted = Boolean.TRUE;
				gp.isPlayerInContactWithVehicle = Boolean.TRUE;
				System.out.println("Luas" + gp.isPlayerInContactWithVehicle);
			}
			if(worldY>=DESTINATION_Y*gp.tileSize) {
				worldY -= speed;
				gp.player.worldY -= speed;
			}else if(worldY <= DESTINATION_Y*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
			
		}
	}
	

}
