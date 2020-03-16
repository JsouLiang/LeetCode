package DynamicProgramming.leetcode.medium;

/**
 * 300. Longest Increasing Subsequence
 *
 */
public class LeetCode300 {
    public int lengthOfLIS(int[] nums) {
        // dp[i] = max(dp[0] ~ dp[i - 1]) + 1
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            int maxValue = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    maxValue = Math.max(maxValue, dp[j]);
                }
            }
            dp[i] = maxValue + 1;
            res = Math.max(res, dp[i]);
//            int currentMax = Integer.MIN_VALUE;
//            int maxIndex = i;
//            for (int j = i - 1; j >= 0; j--) {
//                if (currentMax < dp[j]) {
//                    currentMax = dp[j];
//                    maxIndex = j;
//                }
//            }
//            if (nums[i] > nums[maxIndex]) {
//                dp[i] = currentMax + 1;
//            } else {
//                dp[i] = currentMax;
//            }
        }
        return res;
    }
    public static void main(String[] args) {
        LeetCode300 leetCode300 = new LeetCode300();
//        leetCode300.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        leetCode300.lengthOfLIS(new int[]{4,10, 4,3,8,9});
    }
}
