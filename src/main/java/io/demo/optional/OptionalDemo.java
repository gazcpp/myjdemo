package io.demo.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

	List<String> myList;

	public static void optionalTest() {
		String str = "Test";
		Optional<String> optional = Optional.of(str);
		
		System.out.println("Optional: " + optional + " => " + optional.get());
		
		String mynull=null;
		Optional<String> onull = Optional.ofNullable(mynull);
		
		System.out.println("Null string optionaL: " + onull);
	}

	public static void optionalListTest() {
		
//		String[] tmpList = {"aa","bb","cc","dd","tt","hh"};
		
		
//		List<String> myList = Arrays.asList(tmpList);
//		myList.add(null);
		
//		List<String> newList = getMyList() != null ? getMyList()
//		System.out.println();
	}
	
	public static void optionalUserTest() {
		Optional<User> user = Optional.ofNullable(getUser());
	
		String result = user
				.map(User::getAddress)
				.map(Address::getStreet)
				.orElse("Address no specified");
		
		System.out.println("User Address: " + result);
	}
	
	public static void nullPointerTest() {
		String value = null;
		Optional<String> onull = Optional.ofNullable(value);
		
		try {
			String result = onull.orElseThrow(CustomException::new).toUpperCase();
			
			System.out.println("Result: " + result);

		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	public static User getUser() {
		User u = new User();
		return u;
	}
	
	public List<String> getMyList() {
		return myList;
	}

	public void setMyList(List<String> myList) {
		this.myList = myList;
	}

	public static void main(String[] args) {
	
		optionalTest();
	
	}
}	

 class User{
		private Address address;
//		private String street;
		
		public Address getAddress() {
			return new Address();
		}
		
		
	}

 class Address {
		String street;
		String city;
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	}

 class CustomException extends Exception {
	 
	 public CustomException() {
		 super("String is null");
	 }
 }