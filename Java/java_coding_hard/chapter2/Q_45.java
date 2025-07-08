package java_coding_hard.chapter2;

import java.util.Objects;

public class Q_45 {
    static class Function {
        private final int n;
        private static final int N_UPPER_BOUND = 101;
//
//        public Function(int n) {
//            if (n < 0 && n >= N_UPPER_BOUND) {
//                throw new IndexOutOfBoundsException("예외 발생");
//            }
//
//        }
        // Objects 클래스의 기능을 이용해서 아래와 같이 정의도 가능


        public Function(int n) {
            Objects.checkIndex(n, N_UPPER_BOUND);
            this.n = n;
        }

//        public int yMinusX(int y, int x) {
//            if (x < 0 || x > y || y < 0 || y >= n) {
//                throw new IndexOutOfBoundsException("x 또는 y가 범위를 벗어났습니다.");
//            }
//            return y - x;
//        }

        // 위의 기능을 아래처럼 작성 가능
        public int yMinusX(int x, int y) {
            Objects.checkFromToIndex(x, y, n);
            return y - x;
        }
    }
}
