package io.demo.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MapTest {
    @Test
    public void name() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> expected = Arrays.asList(1,4,9,16,25,36);
        Assert.assertArrayEquals(expected.toArray(), Map.squareList(numbers).toArray());
    }
}
