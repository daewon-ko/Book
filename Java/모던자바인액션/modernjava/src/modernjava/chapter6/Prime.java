package modernjava.chapter6;

import java.util.List;
import java.util.stream.IntStream;

public class Prime {

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    // Collector의 collect기능을 이용한다면?
    public boolean isPrime1(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    public  boolean isPrime2(List<Integer> lists, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return lists.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> i % 2 == 0);
    }

}
