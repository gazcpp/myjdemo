package io.demo.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FilterTest {

    @Test
    public void filterListTest() {
        List<String> names = Arrays.asList("Melisandre", "Sansa", "Jon", "Daenerys", "Joffery");
        List<String> expected = Arrays.asList("Melisandre", "Daenerys", "Joffery");
        Assert.assertArrayEquals(expected.toArray(), Filter.filterList(names).toArray());
    }
}
