package io.demo.threads;

public class MultiThreading2 extends Thread {

	public MultiThreading2(String name) {
		super(name);
	}
	
	public static void connectingToDB() throws InterruptedException {
		Thread.sleep(5000);
	}

	@Override
	public void run() {
		System.out.println("Start thread..." + Thread.currentThread().getName());
		
		try {
			Thread.sleep(1000);
			connectingToDB();
		} catch( InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("End thread..." + Thread.currentThread().getName());

	}
	
	public static void main(String[] args) {
		
		Thread myThread = new MultiThreading2("myThread");
		myThread.start();
		
	}
	
}
