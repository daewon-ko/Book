package java_coding_hard.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

public class Q7문자열숫자변환 {
    public static final String WRONG_NUMBER = "45w";

    // 예외 던지ㅣㄱ
    public static int convertStringToNumber(String number) throws NumberFormatException{
            return Integer.parseInt(number);
    }
}
