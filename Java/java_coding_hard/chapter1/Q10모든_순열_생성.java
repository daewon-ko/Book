package java_coding_hard.chapter1;

public class Q10모든_순열_생성 {
    /**
     * 순열과 관련된 문제는 기본적으로 재귀적 특성을 지닌다.
     */
    public void permuteAndStore(String str) {
        permuteAndPrint("", str);
    }

    /**
     * 출력하는 코드
     *
     * @param prefix
     * @param str
     */
    private void permuteAndPrint(final String prefix, final String str) {
        int n = str.length();
        if (n == 0) {
            System.out.println(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                permuteAndPrint(prefix + str.charAt(i),
                        str.substring(i + 1, n) + str.substring(0, i));
            }
        }
    }
}
