package modernjava.chapter3.section3_5람다캡쳐링;

import java.util.List;
import java.util.function.BiPredicate;

public class lamdaCapturing {
    public static void main(String[] args) {
        int portNumber = 100;
        Runnable r = () -> System.out.println(portNumber);
        Thread thread = new Thread(r);
        thread.start();
//        portNumber = 20;


        BiPredicate<List<String>, String> biPredicate = (list, element) -> list.contains(element);

    }
}
