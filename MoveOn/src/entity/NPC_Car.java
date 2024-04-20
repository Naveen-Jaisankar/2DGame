package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Car extends Entity{
	
	public boolean isInteracted = Boolean.FALSE;
	private static final int DESTINATION_X = 38;
	private static final int DESTINATION_Y = 21;

	public NPC_Car(GamePanel gp) {
		super(gp);
		
		direction = "right";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1 = setup("/npc/bike");
		up2 = setup("/npc/bike");
		down1 = setup("/npc/bike");
		down2 = setup("/npc/bike");
		left1 = setup("/npc/bike");
		left2 = setup("/npc/bike");
		right1 = setup("/npc/bike");
		right2 = setup("/npc/bike");
	}
	public void setDialogue(){
		dialogues[0] = "Press F to Enter the car";
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
				System.out.println("Car" + gp.isPlayerInContactWithVehicle);
			}
			if(worldX<=DESTINATION_X*gp.tileSize) {
				worldX += speed;
				gp.player.worldX += speed;
			}else if(worldX >= DESTINATION_X*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
			
		}
	}
	

}
