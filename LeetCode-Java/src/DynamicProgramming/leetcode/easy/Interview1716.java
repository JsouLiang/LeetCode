package DynamicProgramming.leetcode.easy;

import javax.swing.*;

/**
 * 面试题 17.16. 按摩师
 */
public class Interview1716 {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length <= 2) {
            int res = Integer.MIN_VALUE;
            for (int val : nums) {
                res = Math.max(res, val);
            }
            return res;
        }
        // [0 ~ i] 个预约最大时长
        // max(选第 i 个预约者，不选第 i 个预约者)
        // dp[i] = max(dp[i - 2] + num[i], dp[i - 1])
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; dp[1] = Math.max(nums[0], nums[1]);
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Interview1716 interview1716 = new Interview1716();
//        interview1716.massage(new int[]{1,2,3,1});
//        interview1716.massage(new int[]{2,7,9,3,1});
        interview1716.massage(new int[]{2,1,4,5,3,1,1,3});
    }
}
