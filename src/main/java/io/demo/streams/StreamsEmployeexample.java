package io.demo.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsEmployeexample {

	private static Employee[] employees = {
			new Employee(1, "Emma", 10000),
			new Employee(2, "Eric", 90000),
			new Employee(3, "Edward",80000),
			new Employee(4, "Claudia", 70000)
	};

	private static List<Employee> empList = Arrays.asList(employees);

	public static List<Employee> getEmpList(){
		return empList;
	}
	
	public static List<Employee> increaseSalary(int increase) {
		empList.forEach(emp -> emp.setSalary(emp.getSalary() + increase));
		return empList;
	}
	
	public static List<Employee> createEmployeeList(Integer[] empIds) {
//		Integer[] empIds = {1,2,3,4};
		List<Employee> newList = Stream.of(empIds).map(StreamsEmployeexample::findById).collect(Collectors.toList());
		return newList;
	}
	
	public static Employee findById(int id) {
		Optional<Employee> emp =  empList.stream().filter(e -> e.getId() == id).findFirst();
		return emp.get();
	}
	
	public static List<Employee> filterEmployeeBySalary(double salary) {
		return empList.stream().filter(emp -> emp.getSalary() >= salary).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		
		System.out.println("\nstreamOfArray");
		Stream<Employee> streamOfArray = Stream.of(employees);

		System.out.println(streamOfArray.map( e -> e.toString()).collect( Collectors.joining( "\n" )));
				
		System.out.println("\nstreamOfList");
		Stream<Employee> streamOfList = empList.stream();
		System.out.println(streamOfList.map( e -> e.toString()).collect( Collectors.joining( "\n" )));
		
		System.out.println("\nstreamsOfArrayElements");
		Stream<Employee> streamsOfArrayElements = Stream.of(employees[1], employees[2]);
		System.out.println(streamsOfArrayElements.map( e -> e.toString()).collect( Collectors.joining( "\n" )));
		
		System.out.println("\nempStreamBuilder");
		Stream.Builder<Employee> empStreamBuilder = Stream.builder();
		empStreamBuilder.accept(employees[0]);
		empStreamBuilder.accept(employees[1]);
		empStreamBuilder.accept(employees[2]);
		empStreamBuilder.accept(employees[3]);
		Stream<Employee> streamFromBuilder = empStreamBuilder.build();
		System.out.println(streamFromBuilder.map( e -> e.toString()).collect( Collectors.joining( "\n" )));
	}


}
	


class Employee{
	private int id;
	private String name;
	private double salary;
	
	public Employee(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
	public void salaryIncrement(int percent) {
		this.salary = this.salary + this.salary * percent/100;
	}
	
	
}

