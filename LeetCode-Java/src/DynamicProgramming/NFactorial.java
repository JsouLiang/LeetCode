package DynamicProgramming;
/**
 * DP
 * n 阶乘
 */
public class NFactorial {
    private static int[] results;

    class Solution1 {
        // 暴力搜索
        int f(int n) {
            if (n == 1) {
                return 1;
            }
            return f(n - 1) * n;
        }

        // DP
        int f2(int n) {
            results = new int[n + 1];
            results[0] = 0;
            results[1] = 1;
            for (int i = 2; i <= n; i++) {
                results[i] = results[i - 1] * i;
            }
            return results[n];
        }

    }
}
