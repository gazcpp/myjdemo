package io.demo.streams;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StreamExamplesTest {

	@Test
	public void convertToListtest() {
		StreamExamples.convertStreamToList();
		StreamExamples.fibonacci(10);
		List<Integer> fibo = StreamExamples.getFibonaci(10);
		System.out.println(fibo.toString());
	}

	
	@Test
	public void randomtest() {
		StreamExamples.findRandom();
	}
	
	@Test
	public void uniqueSquaresTest() {
		
		List<Integer> numbers = Arrays.asList(new Integer[] {1,4,7,4,23,4,5,6,3,2,2,1,9,9,7,6,5,9});
		List<Integer> squares = StreamExamples.uniqueSquares(numbers);
		
		System.out.println(squares);
	}
	
	@Test
	public void countOfEmptyStringsTest() {
		
		List<String> words = Arrays.asList(new String[] {"1","4","","  ","","4","23"});
		int c = StreamExamples.countOfEmptyStrings(words);
		
		System.out.println("count: " + c);
	}
	
	@Test
	public void countWitnParallelStreamTest() {
		
		List<String> words = Arrays.asList(new String[] {"1","4","","  ","","4","23"});
		int c = StreamExamples.countWitnParallelStream(words);
		
		System.out.println("count: " + c);
	}
	
	@Test
	public void mergeStringsCollectorsTest() {
		
		List<String> words =  Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");;
		String merged = StreamExamples.mergeStringCollectors(words);
		
		System.out.println("Merged: " + merged);
	}
	
	@Test
	public void highestNumberTest() {
		
		List<Integer> numbers = Arrays.asList(new Integer[] {1,4,7,4,23,4,5,6,3,2,2,1,9,9,7,6,5,9});
		int h = StreamExamples.highestNumber(numbers);
		
		
		System.out.println("Highest number: " + h);
	}
	
	@Test
	public void sumNumbersTest() {
		
		List<Integer> numbers = Arrays.asList(new Integer[] {1,4,7,4,23,4,5,6,3,2,2,1,9,9,7,6,5,9});
		long sum = StreamExamples.sumNumbers(numbers);
		
		
		System.out.println("Sum numbers: " + sum);
	}
	
	@Test
	public void sortTest() {
		
		List<Integer> numbers = Arrays.asList(new Integer[] {1,4,7,4,23,4,5,6,3,2,2,1,9,9,7,6,5,9});
		
		StreamExamples.sort(numbers);

		StreamExamples.sumStream(numbers);
		
	}
	
	@Test
	public void flatenPhonesTest() {
		
		Map<String, List<String>> people = new HashMap<>();
		people.put("John", Arrays.asList("555-1123", "555-3389"));
		people.put("Mary", Arrays.asList("555-2243", "555-5264"));
		people.put("Steve", Arrays.asList("555-6654", "555-3242"));
		
		StreamExamples.flatenPhones(people);
		
	}
	
	@Test
	public void fibonacciTest() {
		
		System.out.println(StreamExamples.fibonacci1(10));
		
	}
	
}