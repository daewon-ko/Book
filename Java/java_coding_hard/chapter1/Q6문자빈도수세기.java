package java_coding_hard.chapter1;

public class Q6문자빈도수세기 {
    public static int countOccurrenceOfCertainCharacter(String str, char character) {
        int count = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }
    public static long countOccurrenceOfCertainCharacterByJava8Style(String str, char ch) {
        return str.chars().filter(c -> c == ch)
                .count();

    }


}
