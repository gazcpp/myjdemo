package io.demo.threads;

//import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class MultiThreadingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void multiThreadtest() {
		Thread t1 = new Thread(new MultiThreading());
		t1.start();		
	}

}
