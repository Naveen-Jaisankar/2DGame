package main;

<<<<<<< HEAD

import com.google.gson.JsonParser;

import entity.Vehicle;
=======
import entity.NPC_OldMan;
//import monster.MON_GreenSlime;
//import java.sql.*;
//import com.google.gson.*;

>>>>>>> refs/remotes/origin/dev

public class AssetSetter {
    GamePanel gp;
    
    private static final String ASSETS_FILE_PATH = "assets.json";
    
    public AssetSetter(GamePanel gp){
        this.gp =gp;
        loadAssets();
    }

    public void setObject(){
<<<<<<< HEAD
=======
    	
>>>>>>> refs/remotes/origin/dev
        
    }
    
    public void loadAssets() {
    	
    }
    
    
    
    public void setNPC() {
<<<<<<< HEAD
    	gp.npc[0] = new Vehicle(gp, "car","down", 23, 42,23,25);
    	gp.npc[1] = new Vehicle(gp, "merchant_down_1","up", 23, 12,23,18);
    	gp.npc[2] = new Vehicle(gp, "oldman_left_1","left", 12, 21,18,21);
    	gp.npc[3] = new Vehicle(gp, "bike","right", 38, 21,27,21);
=======
    	gp.npc[0] = new NPC_OldMan(gp);
    	gp.npc[0].worldX = gp.tileSize*21;
    	gp.npc[0].worldY = gp.tileSize*21;

    }
    public void setMonster(){
//        gp.monster[0] = new MON_GreenSlime(gp);
//        gp.monster[0].worldX = gp.tileSize*23;
//        gp.monster[0].worldY = gp.tileSize*36;
//
//        gp.monster[1] = new MON_GreenSlime(gp);
//        gp.monster[1].worldX = gp.tileSize*23;
//        gp.monster[1].worldY = gp.tileSize*37;


>>>>>>> refs/remotes/origin/dev
    }

}
