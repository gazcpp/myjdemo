package io.demo.threads;

import java.util.function.*;
import java.util.stream.IntStream;

public class MultiThread4 {

	public static void connectingToDB() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void doStuff() {
		System.out.println("Start r thread..." + Thread.currentThread().getName());
		
		try {
			Thread.sleep(1000);
			connectingToDB();
		} catch( InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("End r thread..." + Thread.currentThread().getName());

	}

	
	public static void main(String[] args) {
		
		Runnable r2 = () -> doStuff();
		Thread runSomeThread = new Thread(r2);
		runSomeThread.start();
		
		
	}
	
	
	
	
}
