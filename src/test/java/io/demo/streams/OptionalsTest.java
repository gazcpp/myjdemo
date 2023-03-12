package io.demo.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalsTest {
  
  @Test
  public void filterStreamOfOptionalsTest() {
    List<Optional<String>> listOfOptionals =
      Arrays.asList(
        Optional.empty(), Optional.of("foo"), Optional.empty(), Optional.of("bar"));
    List<String> expected = Arrays.asList("foo","bar");
    List<String> filteredList = Optionals.filterStreamOfOptionals(listOfOptionals);
    Assert.assertArrayEquals(expected.toArray(), filteredList.toArray() );
  }

  @Test
  public void flatmapStreamOfOptionalsTest() {
    List<Optional<String>> listOfOptionals =
      Arrays.asList(
        Optional.empty(), Optional.of("foo"), Optional.empty(), Optional.of("bar"));
    List<String> expected = Arrays.asList("foo","bar");
    List<String> filteredList = Optionals.flatmapStreamOfOptionals(listOfOptionals);
    Assert.assertArrayEquals(expected.toArray(), filteredList.toArray() );
  }


}
