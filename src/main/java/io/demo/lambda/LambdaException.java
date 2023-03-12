package io.demo.lambda;

import java.util.function.BiConsumer;

public class LambdaException {

	public static void process(int[] nums, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i: nums) {
			consumer.accept(i, key);
		}
	}
	
	public static  BiConsumer<Integer, Integer> wrapperLambda( BiConsumer<Integer, Integer> consumer){
		return (v, k) -> {
			try {
				System.out.println((v + k));
			} catch(ArithmeticException e) {
				e.printStackTrace();
			}
		};
	}
	
	public static void main(String[] args) {
		
		int[] nums = {1, 2, 3, 4};
		int key=0;
		
		process(nums,key, wrapperLambda((v,k) -> System.out.println((v/k))));
	}
}
