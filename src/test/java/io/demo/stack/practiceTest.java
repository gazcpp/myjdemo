package io.demo.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class practiceTest {

	StackImplementation<String> mySI;
	
	@Before
	public void setup() {
		mySI = new StackImplementation<String>();
	}
	
	@Test
	public void test() {
		String o = "TEST";
		mySI.push(o);
		assertEquals("TEST", mySI.peek());
		
//		String o1/ = "Second TEST";
		mySI.pop();
		mySI.pop();
//		assertEquals("Second TEST", mySI.peek());

		
	
	}

}
