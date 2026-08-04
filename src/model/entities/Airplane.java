package model.entities;

public class Airplane extends Vehicle {
	
	public static final int AUTONOMY_FACTOR = 10;
	
	public Airplane(String plate, String model, Double loadCapacity) {
		super(plate, model, loadCapacity);
	}

	@Override
	public Double fuelRange() {
		return getLoadCapacity() * AUTONOMY_FACTOR;
	}
	
	@Override
	public String toString() {
		return "Airplane plate: " + getPlate() + ", Model: " + getModel() + "\n"
		+ "Load capacity: " + String.format("%.2f", getLoadCapacity()) 
		+ ", Fuel range: " + String.format("%.2f", fuelRange()) + ".";
	}
}
