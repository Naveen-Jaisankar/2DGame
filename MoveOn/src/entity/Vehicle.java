package entity;

import main.GamePanel;

public class Vehicle extends NonPlayableEntity{
	
	public static final int SPEED = 1;
	
	public Vehicle(GamePanel gp,String VehicleName,String imageName,String direction,int sourceDestinationX,int sourceDestinationY,int sourceCurrentX, int sourceCurrentY,
			int targetDestinationX,int targetDestinationY,int targetCurrentX,int targetCurrentY,int currentMap, int targetMap) {
		super(gp,direction,SPEED,sourceCurrentX,sourceCurrentY);
		setDefaultValues(VehicleName,imageName,direction,sourceCurrentX,sourceCurrentY,sourceDestinationX,sourceDestinationY,targetDestinationX,targetDestinationY,targetCurrentX,targetCurrentY,currentMap,targetMap);
		loadImages();
		setInstructions(VehicleName);
	}

// 	public Vehicle(GamePanel gp, String imageName, String direction, int sourceDestinationX, int sourceDestinationY, int sourceCurrentX, int sourceCurrentY,
//                int targetDestinationX, int targetDestinationY, int targetCurrentX, int targetCurrentY, int mapIndex) {
//     super(gp, direction, SPEED, sourceCurrentX, sourceCurrentY, mapIndex);
//     setDefaultValues(imageName, direction, sourceDestinationX, sourceDestinationY, targetDestinationX, targetDestinationY, targetCurrentX, targetCurrentY);
//     loadImages();
//     setInstructions();
// }


	@Override
	public void setDefaultValues(String VehicleName, String imageName, String direction,int currentX,int currentY,int destinationX, int destinationY,int targetDestinationX,int targetDestinationY,int targetCurrentX,int targetCurrentY,int sourceMap,int targetMap) {
		isInteracted = Boolean.FALSE;
		this.VehicleName = VehicleName;
		this.source_current_x = currentX;
		this.source_current_y = currentY;
		this.source_destination_x = destinationX ;
		this.source_destination_y = destinationY ;
		this.target_current_x = targetCurrentX ;
		this.target_current_y = targetCurrentY ;
		this.target_destination_x = targetDestinationX ;
		this.target_destination_y = targetDestinationY ;
		this.direction = direction;
		this.imageName = imageName;
		this.sourceMap = sourceMap;
		this.targetMap = targetMap;
		
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
	public void setInstructions(String vehicleName) {
		switch (vehicleName) {
			case "Bus":
				dialogues[0] = "Vehicle: BUS, \naverage time taken per KM: 15 minutes,\nAverage CarbonFootPrint: 60 grams\nPress Q to enter";
				break;
			case "Bike":
				dialogues[0] = "Vehicle: BICYCLE, \naverage time taken per KM: 20 minutes,\nAverage CarbonFootPrint: 0 grams\nPress Q to enter";
				break;
			case "Car":
				dialogues[0] = "Vehicle: CAR, \naverage time taken per KM: 5 minutes,\nAverage CarbonFootPrint: 180 grams\nPress Q to enter";
				break;
			default:
				break;
		}	

	}

	@Override
	public void update() {
		int vehicleIndex = gp.cChecker.checkEntity(gp.player, gp.vehicle);
		int npcIndex = gp.cChecker.checkEntity(gp.player, this);
		if(npcIndex!=999 & (gp.keyHandler.qPressed || isInteracted)) {
			// System.out.println("vehicle index:"+vehicleIndex+ "directions:"+this.direction);
			setActions(this.direction,vehicleIndex);
			
			
		}
		
	}

	
	

}
