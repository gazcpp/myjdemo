package io.demo.optional;

import java.util.Arrays;

public class Snippet {
	void optional() {
	
		int min1 = Arrays.stream(new int[] {2,4,6,8,10}).min().orElse(0);
//		Assert.assertEquals(2, min1);
	}
}

