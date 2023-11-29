package java_coding_hard.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Q3_글자와단어뒤집기 {
    /**
     * 일반적으로 작성하는 경우
     */

    public final String whiteSpace = " ";

    public String reveseWord(String str) {
        StringBuilder sb = new StringBuilder();
        String[] words = str.split(whiteSpace);
        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder();

            for (int i = words.length - 1; i >= 0; i--) {
                reversedWord.append(word.charAt(i));
            }
            sb.append(reversedWord).append(whiteSpace);
        }
        return sb.toString();
    }

    /**
     * 함수형 스타일
     */

    public static final Pattern PATTERN = Pattern.compile(" +");

    public static String reversedWord(String str) {
        return PATTERN.splitAsStream(str)
                .map(w -> new StringBuilder(w).reverse())
                .collect(Collectors.joining(" "));
    }


    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "grape");
        List<String> list = fruits.stream().map(w -> w.toUpperCase())
                .collect(Collectors.toUnmodifiableList());
        System.out.println(Arrays.asList(list));
    }


}
