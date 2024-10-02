package modernjava.chapter4;

import modernjava.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;
import static modernjava.Menu.menu;

public class DishEx {
    public static void main(String[] args) {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toUnmodifiableList());
        System.out.println("threeHighCaloricDishNames = " + threeHighCaloricDishNames);


    }

    public static List<Dish> findVegetarianDishes(List<Dish> dishes) {

        return dishes.stream()
                .filter(Dish::isVegetarian)
                .collect(toUnmodifiableList());
    }


    public static List<Dish> dropWhileMethod(List<Dish> dishes) {

        return dishes.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toUnmodifiableList());
    }
}
