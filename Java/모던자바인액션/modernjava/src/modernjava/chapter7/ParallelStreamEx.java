package modernjava.chapter7;

import java.util.stream.Stream;

public class ParallelStreamEx {
    public static void main(String[] args) {

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);
    }


    // 1부터 n까지 더하는 Stream
    public long sequentialSum(long n){
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    // 병렬처리
    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
