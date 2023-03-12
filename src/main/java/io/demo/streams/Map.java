package io.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Map {

    public static List<Integer> squareList(List<Integer> list){
        List<Integer> result = list.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        return result;
    }
}
