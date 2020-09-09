package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
        int[] values = new int[]{9, 8, 1, 2, 2, 2, 3};
        System.out.println(minValue(values));
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (t, n) -> t * 10 + n);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

        Integer sum = integers.stream().reduce(0, Integer::sum);

        return integers.stream()
                .filter(sum % 2 == 0
                        ? (a) -> a % 2 == 0
                        : (a) -> a % 2 != 0).collect(Collectors.toList());
    }
}
