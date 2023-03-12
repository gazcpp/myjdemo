package io.demo.interfaces;

public interface Vehicle {

	/*
	 Starting with Java 8, interfaces can have static and default methods that, 
	 despite being declared in an interface, have a defined behavior.	 
	*/
	static String producer() {
	    return "N&F Vehicles";
	}
	
	/*
	 Default methods are declared using the new default keyword. 
	 These are accessible through the instance of the implementing class and can be overridden.
	 */
	default String getOverview() {
		return "AVT made by " + producer();
	}
	

	
	
	/*
	 Normal method
	 */
	public double getPrice();
}
