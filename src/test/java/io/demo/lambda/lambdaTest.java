package io.demo.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class lambdaTest {


	@Test
	public void mathtest() {
		MyMath.mathExamples(); 
	}
	
	@Test
	public void functionTest() {
		LambdaFunction.functionExample();
		LambdaFunction.chainFunction();
	}

	@Test
	public void listToMapTest() {
		LambdaFunction.convertListtoMap();
	}
	
	@Test
	public void biFunctionTest() {
		LambdaBiFunction.integersExample();
		LambdaBiFunction.chainFunction();
	}
	
	@Test
	public void factoryTest() {
		GPS obj = LambdaBiFunction.factory("40.741895", "-73.989308", GPS::new);
		System.out.println("GPS: "+ obj);
	}
	
	@Test
	public void filterTest() {
		LambdaBiFunction.filterList();
	}
	
	@Test
	public void predicateFilter() {
		LambdaPredicate.filter();
		LambdaPredicate.filterAnd();
		LambdaPredicate.filterNegate();
	}
	
	@Test
	public void lambdaMiscTest() {
		BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
		System.out.println(sum.apply(4, 5));
		
		BiFunction<Integer, Integer, Double> pow = (a,b) -> Math.pow(a, b);
		System.out.println(pow.apply(6, 2));
		
		BiFunction<String, String, List<String>> list = (a, b) -> Arrays.asList(a,b);
		System.out.println(list.apply("Hello", "World"));
		
		Function<Integer, String> toStr = a -> String.valueOf(a);

		String result = sum.andThen(toStr).apply(10, 20);
		System.out.println(result);
		
		String result2 = LambdaBiFunction.powToString(8, 2, 
				(a,b) -> Math.pow(a, b), 
				 a -> String.valueOf(a));
		System.out.println(result2);
		
		String result3 = LambdaBiFunction.convert(8, 2,
				(a,b) -> Math.pow(a, b), 
				 a -> String.valueOf(a));
		System.out.println(result3);

		Function<String, Integer> len = str -> str.length(); 
		List<String> l = Arrays.asList("Beans","Rice","Pasta");
		Map<String, Integer> result4 = LambdaBiFunction.listToMap(l, len);
		System.out.println("result4: " + result4);
	}
	
	@Test
	public void filterAndTest() {
		
        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        Predicate<String> startsWithA = s -> s.startsWith("A");
        
        List<String> listA = list.stream().filter(startsWithA).collect(Collectors.toList());
        List<String> listNoA = list.stream().filter(startsWithA.negate()).collect(Collectors.toList());

        System.out.println("Starts with A: " + listA);
        System.out.println("Not Starts with A: " + listNoA);

	}
}
