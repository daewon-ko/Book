package modernjava.chapter3.section3_4함수형인터페이스사용;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateEx {
    public static void main(String[] args) {

        Predicate<String> nonEmptyString = (String s) -> !s.isEmpty();
        PredicateEx ex = new PredicateEx();
        List<String> nonEmptyStringList = ex.filter(Arrays.asList("1", "2", "3",""), nonEmptyString);
        System.out.println("nonEmptyStringList = " + nonEmptyStringList);

    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;

    }
}
