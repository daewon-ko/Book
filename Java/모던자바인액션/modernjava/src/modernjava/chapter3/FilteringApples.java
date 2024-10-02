package modernjava.chapter3;

import java.util.Arrays;
import java.util.List;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(Color.GREEN, 80),
                new Apple(Color.GREEN, 155),
                new Apple(Color.RED, 120));

        List<Apple> heavyApples = Farmer.filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = Farmer.filterApples(inventory, new AppleGreenColorPredicate());


        // 익명 클래스 사용 -> AppleRedColorPredicate라는 클래스를 만들지 않고 익명클래스화
        List<Apple> redApples = Farmer.filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(Color.RED);
            }
        });

        List<Apple> sameRedApples = Farmer.filterApples(inventory, (Apple apple) -> apple.getColor().equals(Color.RED));


        System.out.println("greenApples = " + greenApples);
        System.out.println("heavyApples = " + heavyApples);
        System.out.println("redApples = " + redApples);


    }
}
