package entity;

import main.GamePanel;

public abstract class NonPlayableEntity extends Entity{
	
	public boolean isInteracted ;
	public int destination_x;
	public int destination_y;
	public static final String NPC_IMAGE_PATH = "/npc/";
	public String imageName;
	
	public NonPlayableEntity(GamePanel gp,String direction, int speed ,int currentX,int currentY) {
		super(gp);
		super.direction = direction;
		super.speed = speed;
		super.worldX = gp.tileSize*currentX;
		super.worldY = gp.tileSize*currentY;
		
	}
	
	public abstract void setDefaultValues(String imageName,String direction,int destinationX,int destinationY);
	
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
		if(gp.keyHandler.fPressed) {
			gp.keyHandler.fPressed=Boolean.FALSE;
			this.isInteracted = Boolean.TRUE;
			gp.isPlayerInContactWithVehicle = Boolean.TRUE;
		}	
		if(direction.equals("down")) {
			if(worldY<=destination_y*gp.tileSize) {
				worldY += speed;
				gp.player.worldY += speed;
			}else if(worldY >= destination_y*gp.tileSize) {
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}		
		}else if(direction.equals("up")) {
			if(worldY>=destination_y*gp.tileSize) {
				worldY -= speed;
				gp.player.worldY -= speed;
			}else if(worldY <= destination_y*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}else if(direction.equals("right")) {
			if(worldX<=destination_x*gp.tileSize) {
				worldX += speed;
				gp.player.worldX += speed;
			}else if(worldX >= destination_x*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}else if(direction.equals("left")) {
			if(worldX>=destination_x*gp.tileSize) {
				worldX -= speed;
				gp.player.worldX -= speed;
			}else if(worldX <= destination_x*gp.tileSize) {
				System.out.println("going to turn of interaction");
				isInteracted = Boolean.FALSE;
				gp.isPlayerInContactWithVehicle = Boolean.FALSE;
			}
		}
	}
	
}