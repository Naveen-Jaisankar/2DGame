package main;

import entity.Bus;
import entity.NPC_Bus;
import entity.NPC_Car;
import entity.NPC_Cycle;
import entity.NPC_Luas;

public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp =gp;
    }

    public void setObject(){
        
    }
    
    public void setNPC() {
//    	gp.npc[0] = new NPC_Bus(gp);
//    	gp.npc[0].worldX = gp.tileSize*23;
//    	gp.npc[0].worldY = gp.tileSize*25;
    	
//    	gp.npc[1] = new NPC_Car(gp);
//    	gp.npc[1].worldX = gp.tileSize*27;
//    	gp.npc[1].worldY = gp.tileSize*21;
//    	
//    	gp.npc[2] = new NPC_Cycle(gp);
//    	gp.npc[2].worldX = gp.tileSize*18;
//    	gp.npc[2].worldY = gp.tileSize*21;
//    	
//    	gp.npc[3] = new NPC_Luas(gp);
//    	gp.npc[3].worldX = gp.tileSize*23;
//    	gp.npc[3].worldY = gp.tileSize*18;
    	
//    	gp.npc[0] = new NPC_Bus(gp);
//    	gp.npc[0].worldX = gp.tileSize*23;
//    	gp.npc[0].worldY = gp.tileSize*25;
    	
//    	gp.npc[0] = new NPC_Bus(gp);
//    	gp.npc[0].worldX = gp.tileSize*23;
//    	gp.npc[0].worldY = gp.tileSize*25;
    	
    	gp.npc[0] = new Bus(gp, "car","down", 23, 42,23,25);
//    	gp.npc[1] = new Bus(gp, "car","down", 23, 30,23,20);
    }

}
