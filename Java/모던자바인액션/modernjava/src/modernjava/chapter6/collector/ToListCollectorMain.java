package modernjava.chapter6.collector;

import modernjava.Dish;
import modernjava.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToListCollectorMain {
    public static void main(String[] args) {
        ToListCollector<Integer> collector = new ToListCollector();

        List<Integer> accumulator = collector.supplier().get();
        collector.accumulator().accept(accumulator, 10);
        collector.accumulator().accept(accumulator, 20);

        List<Integer> result = collector.finisher().apply(accumulator);
        System.out.println(result);

        List<Dish> menu = Menu.menu;
        List<Dish> dishes1 = menu.stream().collect(new ToListCollector<Dish>());
        // 위와 동일한 기능의 ToListCollector
        List<Dish> dishes2 = menu.stream().collect(Collectors.toList());

        List<Dish> dishes = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll
        );

    }
}
