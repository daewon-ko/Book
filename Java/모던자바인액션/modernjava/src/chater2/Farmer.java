package chater2;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    public static List<Apple> filterGreenApples(List<Apple> inventory, Color color) {
        List<Apple> apples = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    public static List<Apple> filterWeightApples(List<Apple> inventory, int weight) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                apples.add(apple);
            }
        }
        return apples;
    }


    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }
}
