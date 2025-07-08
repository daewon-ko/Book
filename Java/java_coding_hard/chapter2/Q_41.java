package java_coding_hard.chapter2;

import java.util.Objects;
import java.util.function.Supplier;

public class Q_41 {

    // NPE를 던지는 방식

    static class Car{
        private final String name;
        private final String color;

        public Car(String name, String color) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            this.color = Objects.requireNonNull(color, "Color cannot be null");
        }
    }

    static class MyObject{
//        public static <T> T requireNonNullElseThrow(T obj, String message){
//            if (obj == null) {
//                throw new IllegalArgumentException(message);
//            }
//            return obj;
//        }

        public static <T> T requireNonNullElseThrowIAE(T obj, Supplier<String> messageSupplier) {
            if (obj == null) {
                throw new IllegalArgumentException(messageSupplier == null ? null : messageSupplier.get());
            }
            return obj;
        }

        public static <T, X extends Throwable> T requireNonNullElseThrow(T obj, X exception) throws X{
            if (obj == null) {
                throw exception;
            }

            return obj;
        }
    }
}
