package modernjava.chapter6;

import modernjava.Dish;
import modernjava.Menu;
import modernjava.Type;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class CollectorsEx {
    public static void main(String[] args) {

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        List<Dish> menu = Menu.menu;
        menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(
                                dish -> dishTags.get(dish.getName()
                        ).stream(),toSet())));

        Map<Type, Map<CaloricLevel, List<Dish>>> collect = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                ));
//        System.out.println(collect.get(Type.MEAT));
        ;
        System.out.println(collect);

        menu.stream()
                .collect(groupingBy(
                        Dish::getCalories,
                        maxBy(comparingInt(Dish::getCalories))
                ));


        Map<Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));


    }


}
