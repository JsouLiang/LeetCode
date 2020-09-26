package DynamicProgramming.leetcode.medium;

public class LeetCode837 {
    /**
     *
     * @param N 获胜时最高得分
     * @param K 获胜时所需最低得分
     * @param W 每次可以从 0 ~ W 范围内选择一张牌
     * @return 获胜概率
     */
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 0;
        }
        /// dp[i] 表示得分从 i 开始获胜的概率
        double[] dp = new double[K + W];
        /// K ~ N 区间获胜的概率为 1
        for (int i = K; i <= N && i < K + W; i++) {
            dp[i] = 1.0;
        }

        for (int i = K - 1; i >= 0; i--) {
            /// 每个 i 都有 W 中选择
            for (int j = 1; j <= W; j++) {
                dp[i] += dp[i + j] / W;
            }
        }
        return dp[0];
    }
}
