package modernjava.chapter6;

import modernjava.Dish;
import modernjava.Menu;
import modernjava.Type;

import java.util.*;
import java.util.stream.Collectors;

public class MaxEx {
    public static void main(String[] args) {
        List<Dish> menu = Menu.menu;
        Optional<Dish> maxCaloriesDish = menu.stream()
                .collect(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                );

        Integer collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);

        String collect1 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println("collect1 = " + collect1);


//        menu.stream()
//                .collect(Collectors.reducing((t -> t.getCalories()));

        menu.stream().map(Dish::getCalories)
                .reduce(Integer::sum);

        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        Map<Type, List<Dish>> collect2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        Map<String, List<Dish>> collect3 = menu.stream()
                .collect(
                        Collectors.groupingBy(
                                d -> {
                                    if (d.getCalories() >= 500) return "고칼로리";
                                    else if (d.getCalories() < 500 && d.getCalories() > 250) return "정상";
                                    return "저칼로리";
                                }

                        )
                );

        menu.stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.filtering(
                                        d -> d.getCalories() > 500,
                                        Collectors.toList()
                                )
                        ));
    }
}
