package model.services;

import model.entities.Order;

public class ShippingProcessor {
	private TaxService taxService;
	private FreightService freightService;
	
	public ShippingProcessor(TaxService taxService, FreightService freightService) {
		this.taxService = taxService;
		this.freightService = freightService;
	}

	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}

	public FreightService getFreightService() {
		return freightService;
	}

	public void setFreightService(FreightService freightService) {
		this.freightService = freightService;
	}
	
	public void processOrder(Order order) { 
		Double taxValue = taxService.tax(order.getGoodsValue()); 
		Double freightValue = freightService.totalFreight(order.getWeight(), order.getDestination());
		Double totalValue = taxValue + freightValue;
		
		System.out.println("Order code: " + order.getCode() + "\n" 
				           + "Tax value: " + String.format("%.2f", taxValue) + "\n"
				           + "Freight value: " + String.format("%.2f", freightValue) + "\n"
				           + "Total value: " + String.format("%.2f", totalValue));
	}
}
