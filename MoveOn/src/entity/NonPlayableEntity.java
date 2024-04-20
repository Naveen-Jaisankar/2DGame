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
	
}
