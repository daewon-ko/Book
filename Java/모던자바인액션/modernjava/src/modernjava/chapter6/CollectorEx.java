package modernjava.chapter6;

import modernjava.Dish;
import modernjava.Menu;
import modernjava.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorEx {
    public static void main(String[] args) {
        List<Dish> menu = Menu.menu;
        Map<Type, Optional<Dish>> collect1 = menu.stream()
                .collect(
                        groupingBy(Dish::getType,
                                maxBy(Comparator.comparingInt(Dish::getCalories)))
                );

        Map<Type, Dish> collect = menu.stream()
                .collect(groupingBy(
                        Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get

                        )
                ));

        System.out.println(collect);
    }
}
