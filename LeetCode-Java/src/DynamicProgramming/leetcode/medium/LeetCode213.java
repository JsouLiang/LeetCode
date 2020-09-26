package DynamicProgramming.leetcode.medium;

public class LeetCode213 {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        /// 由于所有房屋围成一环，所以
        /// 1. 取第一个无法取最后一个，即[0 ~ n - 1] 找最优解
        /// 2. 取最后一个无法取第一个，即[1 ~ n] 找最优解
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int res = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length - 1; i++) {
//            dp[i % 3] = Math.max(dp[(i - 2) % 2] + nums[i], dp[(i - 1) % 3]);
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        dp[0] = 0;
        dp[1] = nums[1]; dp[2] = Math.max(nums[1], nums[2]);
        res = Math.max(res, dp[2]);
        for (int i = 2; i < nums.length; i++) {
//            dp[i % 3] = Math.max(dp[(i - 2) % 2] + nums[i], dp[(i - 1) % 3]);
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode213 leetCode213 = new LeetCode213();
        leetCode213.rob(new int[]{4, 1, 2});
    }
}
