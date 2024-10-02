package modernjava.chapter3.section3_3람다활용;

import java.io.BufferedReader;

@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader br);
}
