package io.demo.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaPerson {

	private List<Person> people = Arrays.asList(
			new Person("Charles", "Smith", 56),
			new Person("John", "Carrey", 56),
			new Person("Carla", "Perez", 56),
			new Person("Chaz", "Clark", 56),
			new Person("Ben", "Steller", 56),
			new Person("Tom", "Brand", 56)
			);
	
	public void practiceSort() {
		Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
		
//		printConditionally(people, p->true, p-> System.out.println(p.toString()));	
		printConditionally(people, p -> true, System.out::println);	

		
		System.out.println("\nStart with S.....");
		printConditionally(people, p->p.getLastName().startsWith("S"), p-> System.out.println(p.toString()));	
	}
	
//	public void printConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
//		people.forEach(p -> {
//			if(predicate.test(p))
//				consumer.accept(p);
//		});
//	}
	
	public void printConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
		people.stream()
			.filter(predicate)
			.forEach(p -> consumer.accept(p));
		
//		forEach(p -> {
//			if(predicate.test(p))
//				consumer.accept(p);
//		});
		
		long count = people.stream()
			.filter(predicate)
			.count();
		
		System.out.println("count: " + count );
	}
	
	//Sort list by last name
	public static void main(String[] args) {
		LambdaPerson lperson = new LambdaPerson();
		lperson.practiceSort();
	}
}
