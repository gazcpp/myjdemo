package io.demo.streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * 
 * https://stackify.com/streams-guide-java-8/
 * 
 * 
 */

public class StreamsEmployeeExampleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void increaseSalaryTest() {
		List<Employee> list = StreamsEmployeexample.increaseSalary(100);
		System.out.println(list.parallelStream().map( e -> e.toString()).collect( Collectors.joining( "\n" )));
	}

	@Test
	public void createEmployeeListTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> list = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println(list.stream().map( e -> e.toString()).collect( Collectors.joining( "\n" )));
		Assert.assertEquals(4, list.size());
	}
	
	@Test
	public void filterEmployeeBySalaryTest() {
		Integer[] empIds = {2,3};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		
		List<Employee> list = StreamsEmployeexample.filterEmployeeBySalary(80000);
		list.forEach(e -> System.out.println(e.toString()));		
		
		Assert.assertEquals(emps, list);
	}
	
	@Test
	public void toArrayTest() {
		Employee[] employees = StreamsEmployeexample.getEmpList().stream().toArray(Employee[]::new);
		Assert.assertArrayEquals(StreamsEmployeexample.getEmpList().toArray(), employees);
	}

	@Test
	public void flattenMapTest() {
		List<List<String>> nestedList = Arrays.asList(
				Arrays.asList("Claudia", "Podol"),
				Arrays.asList("Eric", "Podol"),
				Arrays.asList("Edward", "Podol"),
				Arrays.asList("Emma", "Podol")
				);
	
		String names = nestedList.stream().map( e -> e.toString()).collect( Collectors.joining( "\n" ));

		System.out.println("Unflattened: \n"  + names);

		List<String> flattenNames = nestedList.stream().flatMap(Collection::stream).collect(Collectors.toList());
		
		System.out.println("\nFlattened: \n"  + flattenNames.stream().map( e -> e.toString()).collect( Collectors.joining( "\n" )));

		Assert.assertEquals(flattenNames.size(), nestedList.size()*2 );
	}
	
	@Test
	public void peekTest() {
		
		List<Employee> list = StreamsEmployeexample.getEmpList();
				
		list.stream()
		.peek(e -> e.salaryIncrement(20))
		.peek(System.out::println)
		.collect(Collectors.toList());
	}
	
	@Test
	public void streamPipelineTest() {
		
//	Intermediate operations such as filter() return a new stream on which further processing can be done. Terminal operations, such as forEach(), mark the stream as consumed, after which point it can no longer be used further.
//
//	A stream pipeline consists of a stream source, followed by zero or more intermediate operations, and a terminal operation.
//
//	Here�s a sample stream pipeline, where empList is the source, filter() is the intermediate operation and count is the terminal operation: 
					
		List<Employee> list = StreamsEmployeexample.getEmpList();
		
		Long empCount = list.stream()
			.filter(e -> e.getSalary()>80)
			.count();
		
		Assert.assertEquals(empCount, new Long(1));
	}
	
	
	@Test
	public void shortCircuitTest() {
		
//	Some operations are deemed short-circuiting operations. Short-circuiting operations allow computations on infinite streams to complete in finite time:
		
		Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
		
		List<Integer> collect = infiniteStream.skip(3).limit(5).collect(Collectors.toList());
		
		Assert.assertEquals(collect, Arrays.asList(16,32,64,128,256));
	}
	
	@Test
	public void lazyEvaluationTest() {
//	One of the most important characteristics of streams is that they allow for significant optimizations through lazy evaluations.
//
//	Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.
//
//	All intermediate operations are lazy, so they�re not executed until a result of a processing is actually needed.
//
//	For example, consider the findFirst() example we saw earlier. How many times is the map() operation performed here? 4 times, since the input array contains 4 elements?
//
//	Stream performs the map and two filter operations, one element at a time.
//
//	It first performs all the operations on id 1. Since the salary of id 1 is not greater than 100000, the processing moves on to the next element.
//
//	Id 2 satisfies both of the filter predicates and hence the stream evaluates the terminal operation findFirst() and returns the result.
//
//	No operations are performed on id 3 and 4.
//
//	Processing streams lazily allows avoiding examining all the data when that�s not necessary. 
//  This behavior becomes even more important when the input stream is infinite and not just very large.
		
		Integer[] empIds = {1, 2, 3, 4};
		
		Employee employee = Stream.of(empIds)
				.map(StreamsEmployeexample::findById)
				.filter(e -> e != null)
				.filter(e -> e.getSalary() > 70000)
				.findFirst()
				.orElse(null);
		
		Assert.assertEquals(new Double(90000), new Double(employee.getSalary()));
	}
	
	//https://stackify.com/streams-guide-java-8/
//		Comparison Based Stream Operations
//		sorted
		
	@Test
	public void sortByNameTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		
		System.out.println("UnSorted: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));

		List<Employee> sorted = emps.stream().sorted((e1,e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList());
		
		System.out.println("Sorted: \n"+ sorted.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
	}
	
	@Test
	public void minTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));

		Employee firstEmployee = emps.stream().min((a,b) -> a.getId() - b.getId()).orElseThrow(NoSuchElementException::new);	
		System.out.println("First Employee: \n"+ firstEmployee);
		
		Employee lastEmployee = emps.stream().max((a,b) -> a.getId() - b.getId()).orElseThrow(NoSuchElementException::new);
		System.out.println("Last Employee: \n"+ lastEmployee);
		
		Employee earnMost = emps.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
		System.out.println("Earn most: \n"+ earnMost);
	}
	
	@Test
	public void distinctTest() {
		int[] arr = new int[] {1,4,7,4,23,4,5,6,3,2,2,1,9,9,7,6,5,9};
		
		List<Integer> nums = IntStream.of(arr).boxed().distinct().collect(Collectors.toList());
		
		System.out.println("Distinct: " + nums);
		
//		or
//	    List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
//	    List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
	}
	
	
	@Test
	public void matchTest() {
	    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
	    
	    boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
	    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
	    boolean noneMultipleOfthree = intList.stream().noneMatch(i-> i % 3 == 0);
	    
	    System.out.println("All Even? "+allEven);
	    System.out.println("One Even? "+oneEven);
	    System.out.println("none multiple of three? "+noneMultipleOfthree);
	}
	
	
	@Test
	public void getAvgSalaryTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
		Double ave = emps.stream().mapToDouble(Employee::getSalary).average().orElseThrow(NoSuchElementException::new);
		
		System.out.println("Average Salary: "+ ave);	
	}
	
	@Test
	public void sumSalariesTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
		Double total = emps.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
		System.out.println("Total salaries: "+ total);
	}
	
	@Test
	public void empNamesTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
		String names = emps.stream().map(Employee::getName).collect(Collectors.joining(", "));
		
		System.out.println("Names: "+ names);
	}
	
	@Test
	public void empNamesWitSetTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
		Set<String> names = emps.stream().map(Employee::getName).collect(Collectors.toSet()); 
		
		System.out.println("Names: "+ names);
	}
	
	@Test
	public void empNamesWitCollectionsTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
		
		Vector<String> names = emps.stream().map(Employee::getName).collect(Collectors.toCollection(Vector::new));
		
		System.out.println("Names: "+ names);
	}

	@Test
	public void summarizeDoubleTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
	
		DoubleSummaryStatistics stat = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Stat: "+ stat);
	}
	
	@Test
	public void summarizeStatTest() {
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
	
		DoubleSummaryStatistics stat = emps.stream()
				.mapToDouble(Employee::getSalary)
				.summaryStatistics();
		
		System.out.println("Stat: "+ stat);
	}
	
	@Test
	public void isEvenTest() {
		List<Integer> list = Arrays.asList(2,4,5,6,8);
		
		Map<Boolean, List<Integer>> isEven = list.stream()
				.collect(Collectors.partitioningBy(i -> i%2 ==0));
		
		System.out.println("Even" + isEven.entrySet());
	}
	
	@Test
	public void groupByTest() {
		
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));
	
		Map<Character, List<Employee>> byAlphabeth = emps.stream()
				.collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));
		
		Map<Character, List<Integer>> byId = emps.stream()
				.collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0)), 
						Collectors.mapping(Employee::getId, Collectors.toList())));

		
		System.out.println("Group by Alphabeth" + byAlphabeth);
		
		System.out.println("Group by Id" + byId);

	}
	
	@Test 
	public void reducingTest(){
		
		Integer[] empIds = {1,2,3,4};
		List<Employee> emps = StreamsEmployeexample.createEmployeeList(empIds);
		System.out.println("Emps: \n"+ emps.stream().map(s -> s.toString()).collect(Collectors.joining("\n")));

		Double percentage = 10.0;
		Double salIncrOverhead = emps.stream()
//				.map(Employee::getSalary)
				.collect(Collectors.reducing(0.0, e -> e.getSalary() * percentage / 100, (s1, s2) -> s1 + s2));
		System.out.println("salIncrOverhead" + salIncrOverhead);

		
		Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
		    
		Map<Character, Optional<Employee>> longestNameByAlphabet = emps.stream()
				.collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
						 Collectors.reducing(BinaryOperator.maxBy(byNameLength))));

		System.out.println("longestNameByAlphabet" + longestNameByAlphabet);

	}
	
	/*
	 * Here salaryIncrement() would get executed in parallel on multiple elements of the stream, by simply adding the parallel() syntax.

		This functionality can, of course, be tuned and configured further, 
		if you need more control over the performance characteristics of the operation.
		
		As is the case with writing multi-threaded code, we need to be aware of few things while using parallel streams:
		
		We need to ensure that the code is thread-safe. 
		Special care needs to be taken if the operations performed in parallel modifies shared data.
		We should not use parallel streams if the order in which operations are performed or the order 
		returned in the output stream matters. 
		For example operations like findFirst() may generate the different result in case of parallel streams.
		Also, we should ensure that it is worth making the code execute in parallel. 
		Understanding the performance characteristics of the operation in particular, 
		but also of the system as a whole � is naturally very important here.
	 */
	@Test 
	public void parallelStreamTest(){
		Employee[] arrayOfEmps = {
			      new Employee(1, "Jeff Bezos", 100000.0), 
			      new Employee(2, "Bill Gates", 200000.0), 
			      new Employee(3, "Mark Zuckerberg", 300000.0)
			    };

		System.out.println("arrayOfEmps before: " + arrayOfEmps.toString());
	    List<Employee> empList = Arrays.asList(arrayOfEmps);
	    empList.parallelStream().forEach(e -> e.salaryIncrement(10));
		System.out.println("arrayOfEmps after: " + arrayOfEmps);
	}
	
	/*
	 * Here, we pass Math::random() as a Supplier, which returns the next random number.

		With infinite streams, we need to provide a condition to eventually terminate the processing. 
		One common way of doing this is using limit(). 
		In above example, we limit the stream to 5 random numbers and print them as they get generated.
		
		Please note that the Supplier passed to generate() could be 
		stateful and such stream may not produce the same result when used in parallel.
	 */
	@Test
	public void infiniteStreamTest() {
		Stream.generate(Math::random)
		.limit(5)
		.forEach(System.out::println);
	}
	
	/*
	 * iterate() takes two parameters: an initial value, called seed element and a function which generates 
	 * next element using the previous value. iterate(), by design, is stateful and hence may not be useful in parallel streams:
	 * Here, we pass 2 as the seed value, which becomes the first element of our stream. This value is passed as input to the lambda,
	 *  which returns 4. This value, in turn, is passed as input in the next iteration.

		This continues until we generate the number of elements specified by limit() which acts as the terminating condition.
	 */
	@Test
	public void iterateTest() {
		Stream<Integer> evenNumStream = Stream.iterate(2, i -> i*2);
		List<Integer> list = evenNumStream.limit(5).collect(Collectors.toList());
		System.out.println("list: " + list);
	}
	
	@Test
	public void fileTest() throws IOException {
	    String[] words = {
	    	      "hello", 
	    	      "refer",
	    	      "world",
	    	      "level"
	    	    };
	    
	    String fileName="MyWordsFile.txt";
	    
	    try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(Paths.get(fileName)))){
	    	Stream.of(words).forEach(w::println);
	    }
	}
	
	@Test
	public void getPalindromes() {
		
		 String[] words = {
	    	      "hello", 
	    	      "refer",
	    	      "world",
	    	      "level"
	    	    };
		 
		List<String> list = Arrays.asList(words);
		int lenght = list.get(0).length();
		List<String> palindromes =list.stream()
				.filter(s -> s.length() == lenght)
				.filter(s -> s.compareToIgnoreCase(new StringBuilder(s).reverse().toString()) == 0)
				.collect(Collectors.toList());
		
		System.out.println("palindrome: " + palindromes);
			
	}
	
	@Test
	public void getPalindromesFromFile() throws IOException {
	    String fileName="MyWordsFile.txt";
	    
	    List<String> fileList = Files.lines(Paths.get(fileName)).collect(Collectors.toList());  
	    
	    int length = fileList.get(0).length();
	    
	    List<String> palindromes = fileList.stream()
				.filter(s -> s.length() == length)
				.filter(s -> s.compareToIgnoreCase(new StringBuilder(s).reverse().toString()) == 0)
				.collect(Collectors.toList());
	    
		System.out.println("Palindromes: " + palindromes);

	}
}
