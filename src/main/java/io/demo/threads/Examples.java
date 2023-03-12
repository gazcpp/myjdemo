package io.demo.threads;

import java.util.Arrays;

public class Examples {

	//	Why doesn't this code give compile time error when we are clearly trying to add an integer 
	//  element to an array of Strings

	public static void arraysExample() {
		String[] strArray = new String[2];
		strArray = new String[5];
		
		strArray[0] = "5";
		System.out.println(Arrays.toString(strArray));
	}
	
	public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
		
		int arr1Indx = 0;
		int arr2Indx = 0;
		
		int[] result=new int[arr1.length + arr2.length];
		
		int i=0;
		while(arr1Indx < arr1.length && arr2Indx < arr2.length ) {
			if(arr1[arr1Indx] <= arr2[arr2Indx])
				result[i] = arr1[arr1Indx++];
			else 
				result[i] =  arr2[arr2Indx++];
			i++;
			
			System.out.println(Arrays.toString(result));
		}
		
		while(arr1Indx < arr1.length) {
			result[i++] = arr1[arr1Indx++];
		}
		
		while(arr2Indx < arr2.length) {
			result[i++] = arr2[arr2Indx++];
		}
		 
		System.out.println(Arrays.toString(result));

		return result;
	}
	
	public static void main(String[] args) {
		arraysExample();
		
//		mergeSortedArrays();
	}
}
