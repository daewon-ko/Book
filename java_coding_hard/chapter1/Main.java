package java_coding_hard.chapter1;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static final Pattern PATTERN = Pattern.compile(" + ");
    public static void main(String[] args) {



        String words = "Hello World";
        String result = PATTERN.splitAsStream(words)
                .map(w -> new StringBuilder(w).reverse())
                .collect(Collectors.joining(" "));
        System.out.println(result);
        StringBuilder sb = new StringBuilder(words);
        sb.reverse();
        System.out.println(sb.toString());
    }
}
