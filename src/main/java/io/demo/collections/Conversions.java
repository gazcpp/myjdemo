package io.demo.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Conversions {

	public static void convertArrToList() {
				
		List<Integer> list = Arrays.asList(1, 2, 3);
		System.out.println(list);

		List<Object> list1 = Arrays.asList(new Object[2]);
		System.out.println(list1);

		int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17};
		ArrayList<Integer> primeList = IntStream.of(primes)
			.boxed()
			.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(primeList);

	}
}
