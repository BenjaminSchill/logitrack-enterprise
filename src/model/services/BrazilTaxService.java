package model.services;

public class BrazilTaxService implements TaxService {

	@Override
	public Double tax(Double grossValue) {
		return grossValue * 0.15;
	}
}
