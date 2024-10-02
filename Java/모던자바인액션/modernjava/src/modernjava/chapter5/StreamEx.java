package modernjava.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "World");
        List<String[]> collect = list.stream()
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toUnmodifiableList());

        for (String[] strings : collect) {
            System.out.println(strings);

        }
    }

    public List<Integer> getDouble(List<Integer> numbers) {
        return numbers.stream()
                .map(i -> i * i)
                .collect(Collectors.toUnmodifiableList());
    }


    public static List<int[]> getNumberPair(List<Integer> list1, List<Integer> list2) {

        return list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toUnmodifiableList());
    }


}
