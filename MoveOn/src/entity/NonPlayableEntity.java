package entity;

import main.GamePanel;

public abstract class NonPlayableEntity extends Entity{
	
	public boolean isInteracted ;
	public int source_destination_x;
	public int source_destination_y;
	public int target_current_x;
	public int target_current_y;
	public int target_destination_x;
	public int target_destination_y;
	public static final String NPC_IMAGE_PATH = "/npc/";
	public String imageName;
	
	public NonPlayableEntity(GamePanel gp,String direction, int speed ,int currentX,int currentY) {
		super(gp);
		super.direction = direction;
		super.speed = speed;
		super.worldX = gp.tileSize*currentX;
		super.worldY = gp.tileSize*currentY;
		
	}
	
	public abstract void setDefaultValues(String imageName, String direction, int destinationX, int destinationY,int targetDestinationX,int targetDestinationY,int targetCurrentX,int targetCurrentY);
	
	public abstract void loadImages() ;
	
	public abstract void setInstructions();
	
	public abstract void update();
	
	public void isInteracted() {
		openDialogBox();
	}
	
	public void openDialogBox() {
		gp.gameState = gp.dialougeState;
		super.openDialogBox();
	}
	
	public void setActions(String direction) {
		System.out.println("SetActions called");
		if(gp.keyHandler.qPressed) {
			gp.keyHandler.qPressed=Boolean.FALSE;
			this.isInteracted = Boolean.TRUE;
			gp.isPlayerInContactWithVehicle = Boolean.TRUE;
		}	
		if(direction.equals("down")) {
			if(worldY<=source_destination_y*gp.tileSize) {
				worldY += speed;
				gp.player.worldY += speed;
			}else if(worldY >= source_destination_y*gp.tileSize) {
				gp.player.worldY = source_destination_y*(gp.tileSize-2);
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}		
		}else if(direction.equals("up")) {
			if(worldY>=source_destination_y*gp.tileSize) {
				worldY -= speed;
				gp.player.worldY -= speed;
			}else if(worldY <= source_destination_y*gp.tileSize) {
				gp.player.worldY = source_destination_y*(gp.tileSize+2);
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}else if(direction.equals("right")) {
			if(worldX<=source_destination_x*gp.tileSize) {
				worldX += speed;
				gp.player.worldX += speed;
			}else if(worldX >= source_destination_x*gp.tileSize) {
				gp.player.worldX = source_destination_x*(gp.tileSize-2);
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}else if(direction.equals("left")) {
			if(worldX>=source_destination_x*gp.tileSize) {
				worldX -= speed;
				gp.player.worldX -= speed;
			}else if(worldX <= source_destination_x*gp.tileSize) {
				gp.player.worldX =source_destination_x*(gp.tileSize+2);
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}
		
	}
	
}