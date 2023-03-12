package io.demo.interfaces;

public class VehicleImp implements Vehicle {

	@Override
	public double getPrice() {
		return 100000;
	}

	public static void main(String[] args) {
		
		System.out.println("Producer: " + Vehicle.producer());
		
		VehicleImp vehicle = new VehicleImp();
		
		System.out.println("Overview: " + vehicle.getOverview());

		System.out.println("Price: " + vehicle.getPrice());

	}
}
