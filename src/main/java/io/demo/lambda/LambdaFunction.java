package io.demo.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LambdaFunction {

	
	public static void functionExample() {
		Function<String, Integer> func = x -> x.length();
		Integer apply = func.apply("claudia");
		System.out.println("Function apply - length of Claudia: " + apply);
	}
	
	public static void chainFunction() {
		Function<String, Integer> func = x -> x.length();
		Function<Integer, Integer> func2 = x -> x * 2;

		Integer result = func.andThen(func2).apply("Claudia");
		System.out.println("Function apply - length of Claudia * 2 : " + result);
	}
	
	public static void convertListtoMap() {
		List<String> list = Arrays.asList("node", "c++", "java", "javascript");
		
		Map<String, Integer> myMap = convertListToMap(list, LambdaFunction::getLength);
			
		System.out.println(myMap.toString());
	}
	
	private static <T, R> Map<T,R> convertListToMap(List<T> list, Function<T,R> func){
		
		Map<T,R> result = new HashMap<T,R>();
				
		list.forEach(l -> result.put(l, func.apply(l)));
		
		return result;	
	}
	

    private static Integer getLength(String str) {
        return str.length();
    }
}
