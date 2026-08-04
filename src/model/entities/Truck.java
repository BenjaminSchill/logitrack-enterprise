package model.entities;

import model.exceptions.DomainException;

public class Truck extends Vehicle{
	private Integer numberOfAxles;

	public Truck(String plate, String model, Double loadCapacity, Integer numberOfAxles) {
		super(plate, model, loadCapacity);
		setNumberOfAxles(numberOfAxles);
	}

	public Integer getNumberOfAxles() {
		return numberOfAxles;
	}

	public void setNumberOfAxles(Integer numberOfAxles) {
		if (numberOfAxles == null || numberOfAxles < 2) { 
			throw new DomainException("Error! Number of axles cannot be less than two.");
		}
		this.numberOfAxles = numberOfAxles;
	}

	@Override
	public Double fuelRange() {
		return getLoadCapacity() * numberOfAxles;
	}
	
	@Override
	public String toString() {
		return "Truck plate: " + getPlate() + ", Model: " + getModel() + "\n"
		+ "Load capacity: " + String.format("%.2f", getLoadCapacity()) + ", Number of axles: " + getNumberOfAxles() + "\n"
		+ "Fuel range: " + String.format("%.2f", fuelRange()) + ". ";
	}
}
