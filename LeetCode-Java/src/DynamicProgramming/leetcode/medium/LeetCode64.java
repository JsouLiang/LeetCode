package DynamicProgramming.leetcode.medium;

/**
 * 64. Minimum Path Sum
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class LeetCode64 {
    // right or down
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // dp 表示从 (0, 0) 到 (x, y) 的最短距离
        int[][] dp = new int[grid.length][grid[0].length];
        /**
         * [
         * [1],
         * [1],
         * [3],
         * ]
         */
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        /**
         * [
         * [1, 2, 3],
         * ]
         */
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // dp(x, y) = min(dp(left), dp(right)) + grid(x, y)
        for (int x = 1; x < grid.length; x++) {
            for (int y = 1; y < grid[0].length; y++) {
                dp[x][y] = Math.min(dp[x][y - 1], dp[x - 1][y]) + grid[x][y];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        LeetCode64 leetCode64 = new LeetCode64();
        int res = leetCode64.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        System.out.println(res);
    }
}
