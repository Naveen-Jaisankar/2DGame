package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp =gp;
    }

    public void checkTile(Entity entity){
    	/*
    	 * entity.world.x -> player's starting point
    	 * entity.solidArea.x -> player's collision starting point 
    	 * entity.solidArea.width/height -> length of player's collision 
    	 */
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tile1, tile2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                // System.out.println("Col : " + entityRightCol + "Row : " + entityTopRow);
                tile1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
            
                break;
        }

    }
    
    public int checkObject(Entity entity, boolean player) {
    	
    	int index = 999;
    	
    	for(int i=0;i<gp.obj[1].length;i++) {
    		if(gp.obj[gp.currentMap][i] != null) {
    			//Get entity's solid area position
    			
    			entity.solidArea.x = entity.worldX + entity.solidArea.x;
    			entity.solidArea.y = entity.worldY + entity.solidArea.y;
    			
    			//Get the object's solid area position
    			gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
    			gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
    			
    			switch(entity.direction) {
    			case "up":
    				entity.solidArea.y -= entity.speed;
    				break;
    			case "down":
    				entity.solidArea.y += entity.speed;
    				
    				break;
    			case "left":
    				entity.solidArea.x -= entity.speed;
    				
    				break;
    			case "right":
    				entity.solidArea.x += entity.speed;
    				
    				break;
    			}
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player)index=i;
				}
    			entity.solidArea.x = entity.solidAreaDefaultX;
        		entity.solidArea.y = entity.solidAreaDefaultY;
        		gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
        		gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
    		}
    		
    	}
    	
    	return index;
    }
//    NPC collision
    public int checkEntity(Entity entity, Entity[][] target) {
int index = 999;
    	
    	for(int i=0;i<target[1].length;i++) {
    		if(target[gp.currentMap][i] != null) {
    			//Get entity's solid area position
    			
    			entity.solidArea.x = entity.worldX + entity.solidArea.x;
    			entity.solidArea.y = entity.worldY + entity.solidArea.y;
    			
    			//Get the object's solid area position
    			target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
    			target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
    			
    			switch(entity.direction) {
    			case "up":
    				entity.solidArea.y -= entity.speed;
    				break;
    			case "down":
    				entity.solidArea.y += entity.speed;
    				break;
    			case "left":
    				entity.solidArea.x -= entity.speed;

    				break;
    			case "right":
    				entity.solidArea.x += entity.speed;
    				
    				break;
    			}
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					if(target[gp.currentMap][i]!=entity){
						entity.collisionOn = true;
						index=i;
					}
    					
					
				}
    			entity.solidArea.x = entity.solidAreaDefaultX;
        		entity.solidArea.y = entity.solidAreaDefaultY;
        		target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
        		target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
    		}
    		
    	}
    	
    	return index;
    }
    public boolean checkPlayer(Entity entity) {
    	//Get entity's solid area position

		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		//Get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;	
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;	
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
				
			entity.collisionOn = true;
			contactPlayer = true;

	}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		return contactPlayer;
    	
    }
    
    public int checkEntity(Entity entity, Entity target) {

    	int index = 999;
    	
		if(target != null) {
			//Get entity's solid area position
			
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
			
			//Get the object's solid area position
			target.solidArea.x = target.worldX + target.solidArea.x;
			target.solidArea.y = target.worldY + target.solidArea.y;
			
			switch(entity.direction) {
			case "up":
				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(target.solidArea)) {
					
						entity.collisionOn = true;
					index=1;
				}
				break;
			case "down":
				entity.solidArea.y += entity.speed;
				if(entity.solidArea.intersects(target.solidArea)) {
					
						entity.collisionOn = true;
					index=1;
				}
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				if(entity.solidArea.intersects(target.solidArea)) {
					
						entity.collisionOn = true;
					index=1;
				}
				break;
			case "right":
				entity.solidArea.x += entity.speed;
				if(entity.solidArea.intersects(target.solidArea)) {
					
						entity.collisionOn = true;
					index=1;
				}
				break;
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
    		entity.solidArea.y = entity.solidAreaDefaultY;
    		target.solidArea.x = target.solidAreaDefaultX;
    		target.solidArea.y = target.solidAreaDefaultY;
		}
    		
    	
    	
    	return index;
    }

}
