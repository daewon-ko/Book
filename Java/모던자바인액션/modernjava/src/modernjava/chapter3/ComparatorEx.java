package modernjava.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorEx {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(Color.GREEN, 80),
                new Apple(Color.GREEN, 155),
                new Apple(Color.RED, 120));
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });


    }
}
