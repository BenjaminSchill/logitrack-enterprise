package model.services;

public class StandardFreightService implements FreightService {

	@Override
	public Double freight(Double goodsWeight, String destination) {
		if (destination.equals("Southeast")) {
			return goodsWeight * 5.0;
		} 
		else {
			return goodsWeight * 8.5;
		}
	}
}
