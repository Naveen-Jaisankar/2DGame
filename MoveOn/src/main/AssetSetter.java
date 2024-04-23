package main;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import entity.Vehicle;
import model.VehicleModel;


public class AssetSetter {
    GamePanel gp;
    List<VehicleModel> vehicles;
    
    private static final String ASSETS_FILE_PATH = "D:/Eclipse Workspace/2DGame/MoveOn/res/Assets/assets.json";
    
    public AssetSetter(GamePanel gp){
        this.gp =gp;
        loadAssets();
    }

    public void setObject(){
        
    }
    
    public void loadAssets() {
    	Gson gson = new Gson();
    	try (FileReader reader = new FileReader(ASSETS_FILE_PATH)) {
            Type listType = new TypeToken<List<VehicleModel>>(){}.getType();
            vehicles = gson.fromJson(reader, listType);
            for (VehicleModel vehicle : vehicles) {
                System.out.println(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    public void setNPC() {
    	
    	int i = 0;
    	for (VehicleModel vehicle : vehicles) {
    		gp.npc[i] = new Vehicle(gp, vehicle.getImageName(),vehicle.getDirection(),vehicle.getDestinationX(),vehicle.getDestinationY(),vehicle.getCurrentX(),vehicle.getCurrentY());
    		i++;
    	}
    }

}