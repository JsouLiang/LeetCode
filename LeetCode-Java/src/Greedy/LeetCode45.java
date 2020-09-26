package Greedy;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 */
public class LeetCode45 {
    public int jump(int[] nums) {
        int maxPosition = 0;
        int end = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
    
    /// dp solution
    public int jumpDP(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, n + 1);
            dp[0] = 0;
            for (int i = 1; i < n; i++) {
                /// dp[i] 等于能够跳到第 i 个位置的位置集合 j 中所需步数最小的那个
                for (int j = 0; j < i; j++) {
                    if (nums[j] + j >= i) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                        break;
                    }
                }
            }
            return dp[n - 1];
        }
}
