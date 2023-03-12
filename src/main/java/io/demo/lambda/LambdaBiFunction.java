package io.demo.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaBiFunction {

	public static void integersExample() {
		BiFunction<Integer, Integer, Integer> func = (x1, x2) -> x1 + x2;
		System.out.println("2+5= " + func.apply(2, 5));
		
		BiFunction<Integer, Integer, Double> func2 = (x1, x2) -> Math.pow(x1, x2);
		System.out.println("2^4=" + func2.apply(2, 4));
		
		// take two Integers and return a List<Integer>
		BiFunction<Integer, Integer, List<Integer>> func3 = (x1, x2) -> Arrays.asList(x1 + x2);
		System.out.println("2 and 4 list: " + func3.apply(2, 4));
	}
	
	//BiFunction<T, U, R> + Function<T, R>
	public static void chainFunction() {
		
		BiFunction<Integer, Integer, Double> func1 = (x1, x2) -> Math.pow(x1, x2);

		Function<Double, String> func2 = (input) -> String.valueOf(input);
		
		String result = func1.andThen(func2).apply(2, 4);
		System.out.println("2 and 4 string: " + result);

		String result2 = powToString(2, 4, (x1, x2) -> Math.pow(x1, x2), (input) -> String.valueOf(input));
		System.out.println("2 and 4 string 2: " + result);

		Integer result4 = convert("100", "200",
                (a1, a2) -> a1 + a2,
                (r) -> Integer.valueOf(r));

        System.out.println("result4: " + result4);    // 100200
	}
	
	
    public static <R> R powToString(Integer a1, Integer a2,
            BiFunction<Integer, Integer, Double> func,
            Function<Double, R> func2) {
    	return func.andThen(func2).apply(a1, a2);
	}
    
    public static <A1, A2, R1, R2> R2 convert(A1 a1, A2 a2,
            BiFunction<A1, A2, R1> func,
            Function<R1, R2> func2) {
    	return func.andThen(func2).apply(a1, a2);
	  }
    
    public static <R extends GPS> R factory(String latitude, String longitude, 
    						BiFunction<String, String, R> func) {
    	return func.apply(latitude, longitude);
    }
    
    public static void filterList() {
        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
        List<String> result = filterList(list, 3, LambdaBiFunction::filterByLength);
        System.out.println(result);   
    }
    
    private static <T, R, U> List<R> filterList(List<T> list1, U condition, BiFunction<T,U,R> func){
    	List<R> result = new ArrayList<R>();
    	
        for (T t : list1) {
            R apply = func.apply(t, condition);
            if (apply != null) {
                result.add(apply);
            }
        }

        return result;
    }
    
    private static String filterByLength(String str, Integer size) {
        if (str.length() > size) {
            return str;
        } else {
            return null;
        }
    }
    
    public static <T, R> Map<T,R> listToMap(List<T> list, Function<T,R> func){
    	Map<T, R> map = new HashMap<T,R>();
    	list.forEach(i -> map.put(i, func.apply(i)));
    	return map;
    }
    
}

