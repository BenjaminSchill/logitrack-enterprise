package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import model.entities.Airplane;
import model.entities.Order;
import model.entities.Truck;
import model.entities.Vehicle;
import model.exceptions.DomainException;
import model.services.BrazilTaxService;
import model.services.ShippingProcessor;
import model.services.StandardFreightService;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
		
		ShippingProcessor shippingProcessor = new ShippingProcessor(new BrazilTaxService(),
				new StandardFreightService());
		
		Set<Vehicle> fleet = new HashSet<>();
		fleet.add(new Truck("ABC1234", "Volvo FH", 500.0, 3));
		fleet.add(new Airplane("XYZ5678", "Boeing 737", 2500.0)); 
		
		String path = "orders_input";

		List<Order> successfulOrders = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine(); 
			while (line != null) { 
				String[] fields = line.split(";"); 
				
				try { 
					Integer code = Integer.parseInt(fields[0]); 
					Double weight = Double.parseDouble(fields[1]); 
					Double goodsValue = Double.parseDouble(fields[2]);
					String destination = fields[3];
					String plate = fields[4];
					
					Vehicle associatedVehicle = 
								fleet.stream()
								.filter(v -> v.getPlate()
								.equals(plate))
								.findFirst()
								.orElse(null);
					
					if (associatedVehicle == null) { 
						throw new DomainException("Vehicle not found for plate: " + plate); 
					}
					
					
					Order order = new Order(code, weight, goodsValue, destination, associatedVehicle);
					
					shippingProcessor.processOrder(order);
					
					successfulOrders.add(order);
					
				}
				catch (DomainException e) { 
					System.out.println("Processing error: " + e.getMessage());
				}
				
				line = br.readLine();
			}
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(LocalDateTime.now().format(fmt));
		System.out.println(Instant.now());
		
		double southeastTotal = 
				successfulOrders.stream()
							    .filter(o -> o.getDestination().equals("Southeast"))
							    .mapToDouble(o -> o.getGoodsValue())
							    .sum();
		System.out.printf("Total revenue of the Southeast: %.2f\n", southeastTotal);
		
		successfulOrders.stream()
						.sorted(Comparator.comparing(Order::getCode))
						.forEach(System.out::println);
		
		sc.close();
	}
}
