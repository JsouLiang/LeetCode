package DynamicProgramming.leetcode.medium;

/**
 * 300. Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
public class LeetCode354 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] 表示到达第 i 个位置时，LIS 是多少
        // 起点不是0，终点不是 nums.length - 1,
        // 如果最后返回 dp[nums.length - 1] 则默认 LIS 一定是以最后一个字符结尾
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                // 从前面找：j 位置的点 < i 位置的值
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
//            int maxLIS = 0;
//            int maxJ = 0;
//            for (int j = 0; j < i; j++) {
//                if (dp[j] > maxLIS && nums[i] > nums[j]) {
//                    maxJ = j;
//                    maxLIS = dp[j];
//                }
//            }
//            if (nums[i] > nums[maxJ]) {
//                dp[i] = dp[maxJ] + 1;
//            }
        }

        int maxLIS = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxLIS = Math.max(maxLIS,dp[i]);
        }
        return maxLIS;
    }

    public static void main(String[] args) {
        LeetCode354 leetCode354 = new LeetCode354();
        int res = leetCode354.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        res = leetCode354.lengthOfLIS(new int[]{-2, -1});
        System.out.println(res);
    }
}
