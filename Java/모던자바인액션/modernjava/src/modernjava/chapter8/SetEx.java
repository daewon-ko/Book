package modernjava.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetEx {
    public static void main(String[] args) {

        Set<String> set = Stream.of("하하", "히히", "후후").collect(Collectors.toSet());
        set.add("새로운 것");
        System.out.println("set = " + set);

//        List<Integer> integers = List.of(1, 2, 3, 4);
//        integers.add(6);

        List<String> friends = Arrays.asList("김김김", "고고고고", "이이이");
        friends.set(0, "박박박");
        friends.add("구구구");

    }
}
