package modernjava.chapter3.section3_4함수형인터페이스사용;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerEx {
    public static void main(String[] args) {
        ConsumerEx consumerEx = new ConsumerEx();
        consumerEx.forEach(Arrays.asList("1", "2", "3"),
                i -> System.out.println(i));


    }
    public <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
