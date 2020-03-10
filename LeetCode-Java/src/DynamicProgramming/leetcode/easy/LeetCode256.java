package DynamicProgramming.leetcode.easy;

public class LeetCode256 {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int minCoast = Integer.MAX_VALUE;
        if (costs.length == 1) {
            for (int i = 0; i < 3; i++) {
                minCoast = Math.min(minCoast, costs[0][i]);
            }
            return minCoast;
        }
        int[][] dp = new int[costs.length][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < 3; j++) {
                int prevMinCost = Integer.MAX_VALUE;
                for (int prevJ = 0; prevJ < 3; prevJ++) {
                    if (prevJ == j) {
                        continue;
                    }
                    prevMinCost = Math.min(prevMinCost, dp[i - 1][prevJ]);
                }
                dp[i][j] = prevMinCost + costs[i][j];
            }
            if (i == costs.length - 1) {
                for (int j = 0; j < 3; j++) {
                    minCoast = Math.min(minCoast, dp[costs.length - 1][j]);
                }
            }
        }
        return minCoast;
    }

    public static void main(String[] args) {
        LeetCode256 leetCode256 = new LeetCode256();
        leetCode256.minCost(new int[][] {
                {17,2,17},
                {16,16,5},
                {14,3,19}
        });
    }
}
