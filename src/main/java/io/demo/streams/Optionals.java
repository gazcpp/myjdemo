package io.demo.streams;

import jdk.nashorn.internal.objects.Global;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Optionals {
  public static List<String> filterStreamOfOptionals(List<Optional<String>> listOfOptionals) {
    List<String> filteredList = listOfOptionals.stream()
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toList());
    filteredList.forEach(System.out::println);
    return filteredList;
  }
  public static List<String> flatmapStreamOfOptionals(List<Optional<String>> listOfOptionals) {
    List<String> filteredList = listOfOptionals.stream()
      .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
      .collect(Collectors.toList());

    List<String> filteredList2 = listOfOptionals.stream()
      .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
      .collect(Collectors.toList());
    filteredList.forEach(System.out::println);
    return filteredList;
  }

}
