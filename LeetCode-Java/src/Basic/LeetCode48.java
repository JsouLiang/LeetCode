package Basic;

import java.util.*;

public class LeetCode48 {
    public void rotate(int[][] matrix) {
        int tR = 0, tC = 0;
        int dR = matrix.length - 1, dC = matrix[0].length - 1;
        while (tR < dR) {
            rotate(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void main(String[] args) {
        LeetCode48 leetCode48 = new LeetCode48();
        String res = leetCode48.countAndSay(5);
        System.out.println(res);
    }

    public String countAndSay(int n) {
        return solution(n);
    }

    private String solution(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String preRes = solution(n - 1);
        char prevChar = preRes.charAt(0);
        int count = 1;
        int index = 1;
        String res = "";
        while (index < preRes.length()) {
            if (preRes.charAt(index) == preRes.charAt(index - 1)) {
                count++;
            } else {
                res += count+String.valueOf(prevChar);
                prevChar = preRes.charAt(index);
                count = 1;
            }
            index++;
        }
        res += count+String.valueOf(prevChar);
        return res;
    }

    /**
     *[(tR, tC)
     *     |
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     *              |
     *          (dR, dC)
     * ],
     *
     * [     ---->
     *   ^ [ 5, 1, 9,11], |
     *   | [ 2, 4, 8,10], |
     *   | [13, 3, 6, 7],\|/
     *   | [15,14,12,16]
     *      <-----
     * ],
     * 设置 4 个点分别是：左上(tR, tC), 右上(tR, dC), 右下(dR, dC), 左下(dR, tC)
     * 交换规则 左上->右上->右下->左下->左上
     * (tR, tC + i) -> (tR + i, dC) -> (dR, dC - i) -> (dR - i, tC)
     *
     */
    private void rotate(int[][] matrix, int tR, int tC, int dR, int dC) {
        int count = dC - tC;
        int temp = 0;
        for (int i = 0; i < count; i++) {
            temp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = temp;

        }
    }
}
