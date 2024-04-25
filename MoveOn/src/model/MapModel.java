package model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MapModel {
	private int map_num;
	
	private int target_map_num;
	
	
	@SerializedName("vehicles")
	private List<VehicleModel> vehicle;
	
	public int getMap_num() {
		return map_num;
	}
	public void setMap_num(int map_num) {
		this.map_num = map_num;
	}
	public List<VehicleModel> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<VehicleModel> vehicle) {
		this.vehicle = vehicle;
	}
	
	public int getTarget_map_num() {
		return target_map_num;
	}
	public void setTarget_map_num(int target_map_num) {
		this.target_map_num = target_map_num;
	}
	
	
	
}
