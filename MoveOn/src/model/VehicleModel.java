package model;

import com.google.gson.annotations.SerializedName;

public class VehicleModel {
    
    @SerializedName("sourceMap")
    private int sourceMap;

    @SerializedName("targetMap")
    private int targetMap;

    @SerializedName("vehicle_name")
    private String vehicleName;

    @SerializedName("image_name")
    private String imageName;

    @SerializedName("direction")
    private String direction;

    @SerializedName("sourceDestinationX")
    private int sourceDestinationX;

    @SerializedName("sourceDestinationY")
    private int sourceDestinationY;

    @SerializedName("sourceCurrentX")
    private int sourceCurrentX;

    @SerializedName("sourceCurrentY")
    private int sourceCurrentY;

    @SerializedName("targetDestinationX")
    private int targetDestinationX;

    @SerializedName("targetDestinationY")
    private int targetDestinationY;

    @SerializedName("targetCurrentX")
    private int targetCurrentX;

    @SerializedName("targetCurrentY")
    private int targetCurrentY;

    public int getSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(int sourceMap) {
        this.sourceMap = sourceMap;
    }

    public int getTargetMap() {
        return targetMap;
    }

    public void setTargetMap(int targetMap) {
        this.targetMap = targetMap;
    }
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

	public int getSourceDestinationX() {
		return sourceDestinationX;
	}

	public void setSourceDestinationX(int sourceDestinationX) {
		this.sourceDestinationX = sourceDestinationX;
	}

	public int getSourceDestinationY() {
		return sourceDestinationY;
	}

	public void setSourceDestinationY(int sourceDestinationY) {
		this.sourceDestinationY = sourceDestinationY;
	}

	public int getSourceCurrentX() {
		return sourceCurrentX;
	}

	public void setSourceCurrentX(int sourceCurrentX) {
		this.sourceCurrentX = sourceCurrentX;
	}

	public int getSourceCurrentY() {
		return sourceCurrentY;
	}

	public void setSourceCurrentY(int sourceCurrentY) {
		this.sourceCurrentY = sourceCurrentY;
	}

	public int getTargetDestinationX() {
		return targetDestinationX;
	}

	public void setTargetDestinationX(int targetDestinationX) {
		this.targetDestinationX = targetDestinationX;
	}

	public int getTargetDestinationY() {
		return targetDestinationY;
	}

	public void setTargetDestinationY(int targetDestinationY) {
		this.targetDestinationY = targetDestinationY;
	}

	public int getTargetCurrentX() {
		return targetCurrentX;
	}

	public void setTargetCurrentX(int targetCurrentX) {
		this.targetCurrentX = targetCurrentX;
	}

	public int getTargetCurrentY() {
		return targetCurrentY;
	}

	public void setTargetCurrentY(int targetCurrentY) {
		this.targetCurrentY = targetCurrentY;
	}

    // Existing getters and setters for other fields remain unchanged.

    @Override
    public String toString() {
        return "VehicleModel [sourceMap=" + sourceMap + ", targetMap=" + targetMap
                + ", vehicleName=" + vehicleName + ", imageName=" + imageName + ", direction=" + direction
                + ", sourceDestinationX=" + sourceDestinationX + ", sourceDestinationY=" + sourceDestinationY
                + ", sourceCurrentX=" + sourceCurrentX + ", sourceCurrentY=" + sourceCurrentY + ", targetDestinationX="
                + targetDestinationX + ", targetDestinationY=" + targetDestinationY + ", targetCurrentX="
                + targetCurrentX + ", targetCurrentY=" + targetCurrentY + "]";
    }
}
