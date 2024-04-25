package main;
import java.awt.Rectangle;


public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int map=0;
		int col=0;
		int row=0;
		while(map<gp.maxMap&&col<gp.maxWorldCol&& row<gp.maxWorldRow){
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height =2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			col++;
			if(col==gp.maxWorldCol){
				col=0;
				row++;
				if(row==gp.maxWorldRow){
					row=0;
					map++;
				}
			}

		}
		
		
	}
	
	public void checkEventForVehicles(int vehicleIndex,int sourceDestinationX, int sourceDestinationY,int targetCurrentX, int targetCurrentY,int targetDestinationX, int targetDestinationY, int sourceMap, int targetMap) {
		
		int xDistance = Math.abs(gp.vehicle[sourceMap][vehicleIndex].worldX - previousEventX);
		int yDistance = Math.abs(gp.vehicle[sourceMap][vehicleIndex].worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance>gp.tileSize){
			canTouchEvent = true;
		}
		if(canTouchEvent){
			if(isVehicleInContactWithTeleportTile(vehicleIndex,sourceMap,sourceDestinationX,sourceDestinationY,"any") && gp.isPlayerInContactWithVehicle) {
				teleportPlayerAndVehicle(vehicleIndex,targetMap,targetCurrentX,targetCurrentY,targetDestinationX,targetDestinationY);
			}
		}
	}
	
	
	public void checkEvent() {
		
//		System.out.println("CheckEvent called");
		
		// check player distance from last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance>gp.tileSize){
			canTouchEvent = true;
		}
		if(canTouchEvent){
//			if(hit(0,27,16,"right")) {
//				damagePit(gp.dialougeState);
//			}
//			else if(hit(0,23,12,"up")) {
//				healingPool(23,12,gp.dialougeState);
//			}
//			else if(hit(0,10,39,"any")) {
//				teleport(1,12,13);
//			}
			 if(hit(0,44,14,"any")) {
				teleport(1,29,26);
			}
			 else if(hit(1,29,26,"any")) {
					teleport(0,44,14);
				}
//			 else if(hit(0,45,18,"any")) {
//					teleport(2,8,17);
//				}
//			 
//			 else if(hit(2,8,17,"any")) {
//					teleport(0,45,18);
//				}
			 
		}
		
	}
	
	//This method is similar to hit method
	// public boolean isVehicleInContactWithTeleportTile(int vehicleIndex,int map,int col, int row, String reqDirection) {
	// 	boolean hit = Boolean.FALSE;
	// 	if(map==gp.currentMap){
	// 		gp.vehicle[map][vehicleIndex].solidArea.x = gp.vehicle[map][vehicleIndex].worldX + gp.vehicle[map][vehicleIndex].solidArea.x;
	// 		gp.vehicle[map][vehicleIndex].solidArea.y = gp.vehicle[map][vehicleIndex].worldY + gp.vehicle[map][vehicleIndex].solidArea.y;
	// 		eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
	// 		eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y; 

	// 		System.out.println(gp.vehicle[map][vehicleIndex].solidArea +""+ (eventRect[map][col][row]));
			
	// 		if(gp.vehicle[map][vehicleIndex].solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
	// 			if(gp.vehicle[map][vehicleIndex].direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
	// 				hit = Boolean.TRUE;
	
	// 				previousEventX = gp.vehicle[map][vehicleIndex].worldX;
	// 				previousEventY=gp.vehicle[map][vehicleIndex].worldY;
	// 			}
	// 		}
			
	// 		gp.vehicle[map][vehicleIndex].solidArea.x = gp.vehicle[map][vehicleIndex].solidAreaDefaultX;
	// 		gp.vehicle[map][vehicleIndex].solidArea.y = gp.vehicle[map][vehicleIndex].solidAreaDefaultY;
	// 		eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
	// 		eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
	// 	}
	// 	return hit;
	// }
	
	public boolean isVehicleInContactWithTeleportTile(int vehicleIndex, int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if (map == gp.currentMap) {
			// Temporary variables for adjusted positions
			// System.out.println("worldx: "+gp.vehicle[map][vehicleIndex].worldX+" worldy:"+gp.vehicle[map][vehicleIndex].worldY);
			// System.out.println("playerworldx: "+gp.player.worldX+" worldy:"+gp.player.worldY);

			Rectangle vehicleSolidArea = new Rectangle(gp.vehicle[map][vehicleIndex].solidArea);
			vehicleSolidArea.x += gp.vehicle[map][vehicleIndex].worldX;
			vehicleSolidArea.y += gp.vehicle[map][vehicleIndex].worldY;
			
			Rectangle eventRectangle = new Rectangle(eventRect[map][col][row]);
			eventRectangle.x += col * gp.tileSize;
			eventRectangle.y += row * gp.tileSize;
			// System.out.println("Vehicle Solid Area: " + vehicleSolidArea);
			// System.out.println("Event Rectangle: " + eventRectangle);
			// System.out.println("Intersects: " + vehicleSolidArea.intersects(eventRectangle));
			// System.out.println("Event Done: " + eventRect[map][col][row].eventDone);
			// System.out.println("Direction: " + gp.vehicle[map][vehicleIndex].direction + " Required: " + reqDirection);

	
			if (vehicleSolidArea.intersects(eventRectangle) && !eventRect[map][col][row].eventDone) {
				if (gp.vehicle[map][vehicleIndex].direction.equals(reqDirection) || "any".equals(reqDirection)) {
					hit = true;
					// If necessary, record the vehicle's current world position
					previousEventX = gp.vehicle[map][vehicleIndex].worldX;
					previousEventY = gp.vehicle[map][vehicleIndex].worldY;
				}
			}
		}
		return hit;
	}
	

	public boolean hit(int map,int col, int row, String reqDirection) {
		boolean hit = Boolean.FALSE;
		if(map==gp.currentMap){
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
		eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;	
		
		if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = Boolean.TRUE;

				previousEventX = gp.player.worldX;
				previousEventY=gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
		eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		

		}
		
		
		
		return hit;
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall into a pit!";
		gp.player.life -= 1;
		// eventRect[col][row].eventDone = true;
		canTouchEvent=false;
	}
	
	public void healingPool(int col, int row,int gameState) {
		if(gp.keyHandler.enterPressed) {
			gp.gameState = gameState;
			gp.player.attackCancelled = Boolean.TRUE;
			gp.ui.currentDialogue = "You are Healed.\nYour life and mana has been recovered";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
		}
		
	}

	public void teleportPlayerAndVehicle(int vehicleIndex,int map, int col, int row, int targetDestinationX, int targetDestinationY){
		System.out.println(vehicleIndex+" -" + map+ " -" +col + " -" +row+ " -" +targetDestinationX +" -" + targetDestinationY );
		gp.currentMap = map;
		gp.isPlayerInContactWithVehicle = Boolean.TRUE;
		gp.keyHandler.qPressed = Boolean.TRUE;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		System.out.println(gp.vehicle[map][vehicleIndex]);
		if(gp.vehicle[map][vehicleIndex]!= null){
			gp.vehicle[map][vehicleIndex].worldX = gp.tileSize * col;
			gp.vehicle[map][vehicleIndex].worldY = gp.tileSize * row;
			gp.playSoundEffect(13);
			gp.vehicle[map][vehicleIndex].source_destination_x =targetDestinationX;
			gp.vehicle[map][vehicleIndex].source_destination_y =targetDestinationY;		
			previousEventX = gp.player.worldX;
			previousEventY = gp.player.worldY;
			canTouchEvent = false;
		}
		

	}
	
	public void teleport(int map, int col, int row){
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
		gp.playSoundEffect(13);

	}
}
