package DoublePoint.LeetCode.medium;

import java.util.*;

/**
 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

 示例: 

 输入: s = 7, nums = [2,3,1,2,4,3]
 输出: 2
 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class LeetCode209 {
    public int minSubArrayLen(int s, int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int left = 0; int right = 0;
        int currentSum = 0;
//        List<List<Integer>> result = new ArrayList<>();
        int minimumResult = Integer.MAX_VALUE;
        while (right < nums.length) {
            if (currentSum < s) {
                currentSum += nums[right++];
            }
            while (currentSum >= s) {
                currentSum -= nums[left++];
                if (currentSum <= s) {
                    int leftP = currentSum < s ? left - 1 : left;
                    minimumResult = Math.min(right -  leftP, minimumResult);
//                    List<Integer> currentResult = new ArrayList<>();
//                    for (int j = currentSum < s ? left - 1 : left; j < right; j++) {
//                        currentResult.add(nums[j]);
//                    }
//                    result.add(currentResult);
                }
            }
        }

        return minimumResult == Integer.MAX_VALUE ? 0 : minimumResult;
    }

    public static void main(String[] args) {
        LeetCode209 solution = new LeetCode209();
//        solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3});
        solution.minSubArrayLen(3, new int[]{1, 1});
    }
}
