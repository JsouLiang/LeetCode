package DynamicProgramming.leetcode.medium;

public class LeetCode152 {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int dp[][] = new int[nums.length][2];
        dp[0][0] = nums[0];
        /// 1 表示 > 0
        dp[0][1] = nums[0];
        int res = dp[0][1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                /// 以当前 nums[i]未结尾的最小负数：当前负数 * 前面的dp正数，得到此时最小的负数
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1]);
                /// 以当前 nums[i]未结尾的最大正数：当前负数 * 前面的dp正数，得到此时最小的正数
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0]);
            } else {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
            }
            res = Math.max(dp[i][1], res);
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode152 leetCode152 = new LeetCode152();
        leetCode152.maxProduct(new int[]{-6, -4, -3});
    }
}
