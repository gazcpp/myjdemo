package io.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * https://mkyong.com/java8/java-8-predicate-examples/
 */

public class LambdaPredicate {

	public static void filter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> newlist = list.stream().filter(x -> x > 5).collect(Collectors.toList());
        
        System.out.println(newlist);
	}
	
	public static void filterAnd() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> greaterThan5 = x -> x > 5;
        Predicate<Integer> lessThan8 = x -> x < 8;
        
        
        List<Integer> newlist = list.stream()
        		.filter(greaterThan5.and(lessThan8))
        		.collect(Collectors.toList());
        
        System.out.println(newlist);
	}
	
	
	public static void filterNegate() {

        Predicate<String> startWithA = x -> x.startsWith("A");

        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        List<String> collect = list.stream()
                .filter(startWithA.negate())
                .collect(Collectors.toList());

        System.out.println(collect);
	}
}
