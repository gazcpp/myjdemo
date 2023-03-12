package io.demo.streams;

import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;


public class StreamExamples {
 
	public static void convertStreamToList() {
		Stream<String> language = Stream.of("java", "phyton","node");
		List<String> myList = language.collect(Collectors.toList());

		myList.forEach(System.out::println);
		
		Stream<Integer> nums = Stream.of(1,2,3,4,5);
		List<Integer> mySecondList = nums.filter(x -> x!=3).collect(Collectors.toList());
		
		mySecondList.forEach(System.out::println);

	}
	
	public static void fibonacci(int num) {
		Stream.iterate(new int[] {0,1}, t -> new int[] {t[1], t[0] + t[1]})
			.limit(num)
			.forEach(n -> System.out.println("{" + n[0] + "," + n[1] + "}"));
	}
	
	public static List<Integer> getFibonaci(int series){
		return 		Stream.iterate(new int[] {0,1}, t -> new int[] {t[1], t[0] + t[1]})
				.limit(series)
				.map(n -> n[0])
				.collect(Collectors.toList());

	}
	
	public static void findRandom() {
		Random r = new Random();
		r.ints().limit(10).forEach(System.out::println);
	}

	public static List<Integer> uniqueSquares(List<Integer> numbers) {
		List<Integer> squaresList = numbers.stream()
				.map( i -> i*i)
				.distinct()
				.collect(Collectors.toList());
		return squaresList;
	}

	public static int countOfEmptyStrings(List<String> words) {
		int count = (int) words.stream()
				.filter(s -> s.isEmpty())
				.count();
		return count;
		
	}
	
	public static int countWitnParallelStream(List<String> words) {
		int count = (int) words.parallelStream()
				.filter(s -> s.isEmpty())
				.count();
		return count;
		
	}
	
	public static String  mergeStringCollectors(List<String> words) {
	
		List<String> filtered = words.stream()
					.filter(s -> !s.isEmpty())
					.collect(Collectors.toList());
		
		System.out.println("Filtered List: " + filtered);
		
		String mergedString = words.stream()
				.filter(s -> !s.isEmpty())
				.collect(Collectors.joining(", "));
	
//		System.out.println("Merged String: " + mergedString);
		
		return mergedString;
	}
	
	public static int highestNumber(List<Integer> numbers) {
		IntSummaryStatistics highest = numbers.stream()
				.mapToInt(x -> x)
				.summaryStatistics();
		
		return highest.getMax();
	}
	
	public static long sumNumbers(List<Integer> numbers) {
		IntSummaryStatistics highest = numbers.stream()
				.mapToInt(x -> x)
				.summaryStatistics();
		
		return highest.getSum();
	}
	
	public static void flatenPhones(Map<String, List<String>> people) {
	
//		Map<String, List<String>> people = new HashMap<>();
//		people.put("John", Arrays.asList("555-1123", "555-3389"));
//		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
//		people.put("Steve", Arrays.asList("555-6654", "555-3242"));
		 
		List<String> phones = people.values().stream()
		  .flatMap(Collection::stream)
		    .collect(Collectors.toList());
		
		System.out.println(phones);
		//[555-6654, 555-3242, 555-1123, 555-3389, 555-2243, 555-5264]

	}
	
	
	public static void sort(List<Integer> numbers) {
		System.out.println("unsorted: " + numbers);
		Collections.sort(numbers, (s1, s2) -> s1.compareTo(s2));
		System.out.println("sorted: " + numbers);
	}
	
	public static void sumStream(List<Integer> numbers) {
		int sum = numbers.stream().mapToInt(i -> i).sum();
		System.out.println("sum: " + sum);
	}
	
	public static List<Integer> fibonacci1(int series) {

		List<Integer> fib = Stream.iterate(new int[] {0,1}, s-> new int[] {s[1], s[0] + s[1]})
		.limit(series)
		.map(n -> n[0])
		.collect(Collectors.toList());
		
		return fib;
		
	}
	
}
