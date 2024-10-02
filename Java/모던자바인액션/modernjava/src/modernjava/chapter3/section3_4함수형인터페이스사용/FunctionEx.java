package modernjava.chapter3.section3_4함수형인터페이스사용;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionEx {

    public static void main(String[] args) {
        FunctionEx functionEx = new FunctionEx();
        List<Integer> map = functionEx.map(Arrays.asList("12334", "123", "555555555"),
                s -> s.length());
        System.out.println("map = " + map);

    }

    public <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
