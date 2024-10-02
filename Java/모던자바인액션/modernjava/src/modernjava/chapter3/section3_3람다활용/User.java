package modernjava.chapter3.section3_3람다활용;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {
    public static void main(String[] args) throws Exception, IOException {



    }

    public static String processFileV1() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    // 설정은 그대로지만 실행하는 것을 함수형 인터페이스로 파라미터화
    public static String processFileV2(BufferedReaderProcessor p) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }
}
