package DP;

/**
 * DP
 * Created by X-Liang
 * 2017-10-07-17:14
 *
 * @Description: 斐波那契数列
 */
public class Fib {

    private static int[] results;

    class Solution1 {
        private int[] fibs;
        // 暴力搜索
        int fib(int n) {
            if (n == 1) {
                return 1;
            }
            return fib(n - 1) + fib(n - 2);
        }

        // 记忆化搜索
        private int solution(int n) {
            results = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                results[i] = -1;
            }
            return results[n];
        }

        int fib2(int n) {
            if (n == 1) {
                return 1;
            }
            if (results[n] != -1) {
                return results[n];
            }
            results[n] = fib(n - 1) + fib(n - 2);
            return results[n];
        }


        // DP
        int fib3(int n) {
            fibs = new int[n + 1];
            fibs[0] = 0;
            fibs[1] = 1;
            fibs[2] = 1;
            for (int i = 3; i <= n; i++) {
                fibs[i] = fibs[i - 1] + fibs[i - 2];
            }
            return fibs[n];
        }

    }
}

