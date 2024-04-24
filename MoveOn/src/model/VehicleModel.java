package model;

import com.google.gson.annotations.SerializedName;

public class VehicleModel {
	
	@SerializedName("vehicle_name")
	private String vehicleName;
	
	@SerializedName("image_name")
    private String imageName;
	
	@SerializedName("direction")
    private String direction;
	
	@SerializedName("destinationX")
    private int destinationX;
	
	@SerializedName("destinationY")
    private int destinationY;
	
	@SerializedName("currentX")
    private int currentX;
	
	@SerializedName("currentY")
    private int currentY;
	
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getDestinationX() {
		return destinationX;
	}
	public void setDestinationX(int destinationX) {
		this.destinationX = destinationX;
	}
	public int getDestinationY() {
		return destinationY;
	}
	public void setDestinationY(int destinationY) {
		this.destinationY = destinationY;
	}
	public int getCurrentX() {
		return currentX;
	}
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}
	public int getCurrentY() {
		return currentY;
	}
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	
	@Override
	public String toString() {
		return "VehicleModel [vehicleName=" + vehicleName + ", imageName=" + imageName + ", direction=" + direction
				+ ", destinationX=" + destinationX + ", destinationY=" + destinationY + ", currentX=" + currentX
				+ ", currentY=" + currentY + "]";
	}
    
    
}