package model.services;

public interface FreightService {
	Double freight(Double goodsWeight, String destination);

	default Double totalFreight(Double goodsWeight, String destination) {
		Double freight = freight(goodsWeight, destination);
		return freight * 1.02;
	}
}
