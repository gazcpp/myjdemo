package io.demo.streams;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<String> filterList(List<String> list){
        List<String> longNames = list.stream()
                .filter(str -> str.length() > 6)
                .collect(Collectors.toList());
        return longNames;
    }
}
