package DynamicProgramming.leetcode.easy;

public class LeetCode53 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // dp[i] = max(dp[i - 1], 0) + nums[i]
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0 )+ nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode53 leetCode53 = new LeetCode53();
        leetCode53.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
}
