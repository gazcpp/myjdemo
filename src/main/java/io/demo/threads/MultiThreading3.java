package io.demo.threads;

public class MultiThreading3 {

	public static void connectingToDB() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void runSomething() {
		
		
		Runnable r = new Runnable(){

			@Override
			public void run() {
				System.out.println("Start r thread..." + Thread.currentThread().getName());
				
				try {
					Thread.sleep(1000);
					connectingToDB();
				} catch( InterruptedException e) {
					e.printStackTrace();
				}
			
				System.out.println("End r thread..." + Thread.currentThread().getName());

			}
		};

		Thread runSomeThread = new Thread(r);
		runSomeThread.start();

	}
	
	public static void main(String[] args) {
		runSomething();
		
	}
}
