package io.demo.threads;


public class MultiThreading implements Runnable {

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
		Thread t1 = new Thread(new MultiThreading());
		t1.start();
	}
	
}
