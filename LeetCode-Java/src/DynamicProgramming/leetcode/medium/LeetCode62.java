package DynamicProgramming.leetcode.medium;

import Basic.BinarySearch.LeetCode35;

/**
 * 62. Unique Paths
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 */
public class LeetCode62 {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < m; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        int res = dp[n - 1][m - 1];
        return res;
    }

    public static void main(String[] args) {
        LeetCode62 leetCode62 = new LeetCode62();
        leetCode62.uniquePaths(3, 2);
        leetCode62.uniquePaths(7, 3);
    }
}
