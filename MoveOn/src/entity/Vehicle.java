package entity;

import main.GamePanel;

public class Vehicle extends NonPlayableEntity{
	
	public static final int SPEED = 1;
	
	public Vehicle(GamePanel gp,String imageName,String direction,int destinationX,int destinationY,int currentX, int currentY) {
		super(gp,direction,SPEED,currentX,currentY);
		setDefaultValues(imageName,direction,destinationX,destinationY);
		loadImages();
		setInstructions();
	}


	@Override
	public void setDefaultValues(String imageName, String direction, int destinationX, int destinationY) {
		isInteracted = Boolean.FALSE;
		this.destination_x = destinationX ;
		this.destination_y = destinationY ;
		this.direction = direction;
		this.imageName = imageName;
		
	}

	@Override
	public void loadImages() {
		up1 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		up2 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		down1 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		down2 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		left1 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		left2 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		right1 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
		right2 = setup(NPC_IMAGE_PATH + imageName,gp.tileSize,gp.tileSize);
	}

	@Override
	public void setInstructions() {
		dialogues[0] = "Press q to Enter the" + this.name;		
	}

	@Override
	public void update() {
		int npcIndex = gp.cChecker.checkEntity(gp.player, this);
		if(npcIndex!=999 & (gp.keyHandler.qPressed || isInteracted)) {
			
			setActions(this.direction);
//			if(gp.keyHandler.fPressed) {
//				gp.keyHandler.fPressed=Boolean.FALSE;
//				isInteracted = Boolean.TRUE;
//				gp.isPlayerInContactWithVehicle = Boolean.TRUE;
//				System.out.println("Bus" + gp.isPlayerInContactWithVehicle);
//			}
//			
//			if(worldY<=destination_y*gp.tileSize) {
//				worldY += speed;
//				gp.player.worldY += speed;
//			}else if(worldY >= destination_y*gp.tileSize) {
//				System.out.println("going to turn of interaction");
//				isInteracted = Boolean.FALSE;
//				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
//			}
			
		}
		
	}

	
	

}
