package java_coding_hard.chapter1;

import java.util.*;

public class Q5_모음과_자음_세기 {
    private static final Set<Character> allVowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static Map<String, Integer> countVowelsAndConsonants(String str) {


        Map<String, Integer> counts = new HashMap<>();
        int vowel = 0;
        int consonants = 0;

        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (allVowels.contains(c)) {
                vowel++;
            } else if (c >= 'a' && c <= 'z') {
                consonants++;
            }
        }

        counts.put("vowel", vowel);
        counts.put("consonants", consonants);

        return counts;

    }

    public Map<String, Long> countByJava8Style(String str) {
        int vowel, consonants = 0;
        str = str.toLowerCase();

        long vowels = str.chars()
                .filter(c -> allVowels.contains(c))
                .count();
        long consonant = str.chars()
                .filter(c -> !allVowels.contains(c))
                .filter(c -> c >= 'a' && c <= 'z')
                .count();

        Map<String, Long> result = new HashMap<>();
        result.put("vowels", vowels);
        result.put("consonants", consonant);

        return result;
    }






}
