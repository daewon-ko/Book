package modernjava.chapter5;

import java.util.List;
import java.util.Optional;

public class ReducingEx {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 5, 6, 9);
        Optional<Integer> result = numbers.stream().reduce(Integer::max);
        System.out.println(result.get());
    }
}
