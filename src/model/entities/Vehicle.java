package model.entities;

import java.util.Objects;

import model.exceptions.DomainException;

public abstract class Vehicle {
	private String plate;
	private String model; 
	private Double loadCapacity;
	
	public Vehicle(String plate, String model, Double loadCapacity) {
		setPlate(plate);
		setModel(model);
		setLoadCapacity(loadCapacity);
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		if (plate == null || plate.isEmpty()) { 
			throw new DomainException("Error! You need to enter the license plate.");
		}
		this.plate = plate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		if (model == null || model.trim().isEmpty()) { 
			throw new DomainException("Error! A valid vehicle must be assigned."); 
		}
		this.model = model;
	}

	public Double getLoadCapacity() {
		return loadCapacity;
	}

	public void setLoadCapacity(Double loadCapacity) {
		if (loadCapacity <= 0) { 
			throw new DomainException("Error! Load capacity must be greater than zero.");
		}
		this.loadCapacity = loadCapacity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(plate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(plate, other.plate);
	}
	
	public abstract Double fuelRange();
}
