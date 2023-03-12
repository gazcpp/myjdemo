package io.demo.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.util.stream.IntStream;

public class MyMath {
		
	public PerformOperation isOdd() {
		return (num) -> num % 2 != 0;
	}
	
	public PerformOperation isPrime() {
		return num -> IntStream
				.range(2, (int) Math.sqrt(num+1))
				.noneMatch(i -> num % i == 0);
	}
	
	public PerformOperation isPalindrome() {
		return num -> (""+num).equals(""+new StringBuilder((""+num)).reverse());
	}

	public boolean checker(PerformOperation op, int num) {
		return op.operation(num);
	}
	
	public static void mathExamples() {
				
		MyAdd myAdd = (int a, int b) -> a+b;
		System.out.println("10 + 7" + " = " + myAdd.add(10,  7) );
	}
	
	/**
The first line contains an integer,  (the number of test cases).

The  subsequent lines each describe a test case in the form of  space-separated integers:
The first integer specifies the condition to check for ( for Odd/Even,  for Prime, or  for Palindrome). The second integer denotes the number to be checked.

5
1 4
2 5
3 898
1 3
2 12
	 */
	
	public static void main(String[] args) throws IOException  {
		
		  MyMath ob = new MyMath();
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  int T = Integer.parseInt(br.readLine());
		  PerformOperation op;
		  boolean ret = false;
		  String ans = null;
		  while (T--> 0) {
		   String s = br.readLine().trim();
		   StringTokenizer st = new StringTokenizer(s);
		   int ch = Integer.parseInt(st.nextToken());
		   int num = Integer.parseInt(st.nextToken());
		   if (ch == 1) {
		    op = ob.isOdd();
		    ret = ob.checker(op, num);
		    ans = (ret) ? "ODD" : "EVEN";
		   } 
		   else if (ch == 2) {
		    op = ob.isPrime();
		    ret = ob.checker(op, num);
		    ans = (ret) ? "PRIME" : "COMPOSITE";
		   } else if (ch == 3) {
		    op = ob.isPalindrome();
		    ret = ob.checker(op, num);
		    ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

		   }
		   System.out.println(ans);
		  }
	}
}

	interface PerformOperation {
		boolean operation(int num);
	}
	
	interface MyAdd{
		int add(int a, int b);
	}

