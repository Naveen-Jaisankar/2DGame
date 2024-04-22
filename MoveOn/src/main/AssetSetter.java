package main;


import com.google.gson.JsonParser;

import entity.Vehicle;

public class AssetSetter {
    GamePanel gp;
    
    private static final String ASSETS_FILE_PATH = "assets.json";
    
    public AssetSetter(GamePanel gp){
        this.gp =gp;
        loadAssets();
    }

    public void setObject(){
        
    }
    
    public void loadAssets() {
    	
    }
    
    
    
    public void setNPC() {
    	gp.npc[0] = new Vehicle(gp, "car","down", 23, 42,23,25);
    	gp.npc[1] = new Vehicle(gp, "merchant_down_1","up", 23, 12,23,18);
    	gp.npc[2] = new Vehicle(gp, "oldman_left_1","left", 12, 21,18,21);
    	gp.npc[3] = new Vehicle(gp, "bike","right", 38, 21,27,21);
    }

}
