package java_coding_hard.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q1_문자개수세기 {
    public Q1_문자개수세기() throws IOException {
    }

    public Map<Character, Integer> countDuplicateCharacters(String str) {
        Map<Character, Integer> result = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;

    }

    public Map<Character, Long> countDuplicateCharacters2(String str) {
        Map<Character, Long> result = str.chars().
                mapToObj(c -> (char) c).
                collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return result;
    }

    



}

