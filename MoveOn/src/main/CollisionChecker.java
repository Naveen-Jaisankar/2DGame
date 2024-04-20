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
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tile1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tile1].colllision == true || gp.tileM.tile[tile2].colllision == true){
                    entity.collisionOn=true;
                }
            
                break;
        }

    }
    
    public int checkObject(Entity entity, boolean player) {
    	
    	int index = 999;
    	
    	for(int i=0;i<gp.obj.length;i++) {
    		if(gp.obj[i] != null) {
    			//Get entity's solid area position
    			
    			entity.solidArea.x = entity.worldX + entity.solidArea.x;
    			entity.solidArea.y = entity.worldY + entity.solidArea.y;
    			
    			//Get the object's solid area position
    			gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
    			gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
    			
    			switch(entity.direction) {
    			case "up":
    				entity.solidArea.y -= entity.speed;
    				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
    					if(gp.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if(player)index=i;
    				}
    				break;
    			case "down":
    				entity.solidArea.y += entity.speed;
    				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
    					if(gp.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if(player)index=i;
    				}
    				break;
    			case "left":
    				entity.solidArea.x -= entity.speed;
    				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
    					if(gp.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if(player)index=i;
    				}
    				break;
    			case "right":
    				entity.solidArea.x += entity.speed;
    				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
    					if(gp.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if(player)index=i;
    				}
    				break;
    			}
    			entity.solidArea.x = entity.solidAreaDefaultX;
        		entity.solidArea.y = entity.solidAreaDefaultY;
        		gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
        		gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
    		}
    		
    	}
    	
    	return index;
    }
    
    
//    NPC collision
    public int checkEntity1(Entity entity, Entity[] target) {

    	int index = 999;
    	
    	for(int i=0;i<target.length;i++) {
    		if(target[i] != null) {
    			//Get entity's solid area position
    			
    			entity.solidArea.x = entity.worldX + entity.solidArea.x;
    			entity.solidArea.y = entity.worldY + entity.solidArea.y;
    			
    			//Get the object's solid area position
    			target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
    			target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
    			
    			switch(entity.direction) {
    			case "up":
    				entity.solidArea.y -= entity.speed;
    				if(entity.solidArea.intersects(target[i].solidArea)) {
    					
    						entity.collisionOn = true;
    					index=i;
    				}
    				break;
    			case "down":
    				entity.solidArea.y += entity.speed;
    				if(entity.solidArea.intersects(target[i].solidArea)) {
    					
    						entity.collisionOn = true;
    					index=i;
    				}
    				break;
    			case "left":
    				entity.solidArea.x -= entity.speed;
    				if(entity.solidArea.intersects(target[i].solidArea)) {
    					
    						entity.collisionOn = true;
    					index=i;
    				}
    				break;
    			case "right":
    				entity.solidArea.x += entity.speed;
    				if(entity.solidArea.intersects(target[i].solidArea)) {
    					
    						entity.collisionOn = true;
    					index=i;
    				}
    				break;
    			}
    			entity.solidArea.x = entity.solidAreaDefaultX;
        		entity.solidArea.y = entity.solidAreaDefaultY;
        		target[i].solidArea.x = target[i].solidAreaDefaultX;
        		target[i].solidArea.y = target[i].solidAreaDefaultY;
    		}
    		
    	}
    	
    	return index;
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
    
    public void checkPlayer(Entity entity) {
    	//Get entity's solid area position
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		//Get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
					entity.collisionOn = true;
		
			}
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
					entity.collisionOn = true;
				
			}
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
					entity.collisionOn = true;
				
			}
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				
					entity.collisionOn = true;
				
			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    	
    }

}
