package modernjava.chapter8;

import java.util.Map;

public class MapEntryEx {
    public static void main(String[] args) {
        Map<String, Integer> maps
                = Map.ofEntries(Map.entry("김김김", 600),
                Map.entry("이이이", 200),
                Map.entry("히히히", 500));

        maps.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(System.out::println);

    }
}
