package java_coding_hard.chapter1;

import java.util.LinkedHashMap;
import java.util.Map;

public class Q2_반복되지않는첫번쨰문자찾기 {
    public char firstNonRepeatedCharacter(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        /**
         * LinkedHashMap이 아니라 HashMap을 이용할경우, map에 저장되는 순서가 보장되지
         * 않으므로 하단의 두 번째 for문에서 찾는 key가 첫번째 반복되지 않는 첫번째 문자임을
         * 보장할 수 없음.
         * 따라서 반드시 LinkedHashMap을 사용해줘야함(순서를 보장하기 떄문)
         */
        for (char c : str.toCharArray()) {
            map.compute(c, (k, v) -> (v == null) ? 1 : v++);
            /**
             * c가 존재하지 않으면(처음 순회하는 문자) value는 1을 삽입
             * c가 기 존재하던 것이면(이미 순회한 문자) value는 기존 value에 1을 더한다.
             * 이렇게 해서 map을 조작하고, map.entrySet().getValue()를 이용하여 1인값이
             * 반복되지 않은 첫번째 문자이다.
             *
             *
             */
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }
}
