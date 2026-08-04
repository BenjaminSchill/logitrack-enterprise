package model.entities;

import model.exceptions.DomainException;

public class Order {
	private Integer code; 
	private Double weight;
	private Double goodsValue; 
	private String destination;
	private Vehicle vehicle;
	
	public Order(Integer code, Double weight, Double goodsValue, String destination, Vehicle vehicle) {
		setCode(code);
		setWeight(weight);
		setGoodsValue(goodsValue);
		setDestination(destination);
		setVehicle(vehicle);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		if (code == null || code <= 0) {
			throw new DomainException("Error! Order code must be greater than zero.");
		}
		this.code = code;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		if (weight == null || weight <= 0) { 
			throw new DomainException("Error! Weight must be greater than zero."); 
		}
		this.weight = weight;
	}

	public Double getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(Double goodsValue) {
		if (goodsValue == null || goodsValue <= 0) { 
			throw new DomainException("Error! Value of the goods must be greater than zero."); 
		}
		this.goodsValue = goodsValue;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		if (destination == null || destination.trim().isEmpty()) {
			throw new DomainException("Error! Destination region cannot be empty.");
		}
		this.destination = destination;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			throw new DomainException("Error! A valid vehicle must be assigned to the order.");
		}
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Order code: " + code + "\n"
			 + "Weight: " + String.format("%.2f", weight) + "\n" 
			 + "Goods value: " + String.format("%.2f", goodsValue) + "\n" 
			 + "Destination: " + destination + "\n"
			 + "Vehicle: " + vehicle;
	}
}