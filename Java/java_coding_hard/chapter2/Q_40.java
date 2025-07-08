package java_coding_hard.chapter2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Q_40 {

    public static List<Integer> evenInteger(List<Integer> integers) {
        // 필터링된 짝수 정수 리스트를 반환
        if (Objects.isNull(integers)) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer number : integers) {
            if (Objects.nonNull(number) && number % 2 == 0) {
                evenNumbers.add(number);
            }
        }
        return evenNumbers;
    }

    // 함수형 스타일
    public static Integer evenIntegerFunctional(List<Integer> integers) {
        // 필터링된 짝수 정수 리스트를 반환
        if (Objects.isNull(integers)) {
            return null;
        }

        return integers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue).sum();
    }
}
