package DoublePoint.LeetCode.medium;

import java.util.Arrays;

/**
 * 259. 较小的三数之和
 * 给定一个长度为 n 的整数数组和一个目标值 target，
 * 寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
 * <p>
 * 输入: nums = [-2,0,1,3], target = 2
 * 输出: 2
 * 解释: 因为一共有两个三元组满足累加和小于 2:
 *      [-2,0,1]
 * [-2,0,3]
 */
public class LeetCode259 {
    public int threeSumSmaller(int[] nums, int target) {
//        int count = 0;
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = i + 1; j < nums.length - 1; j++) {
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (nums[i] + nums[j] + nums[k] < target) {
//                        count++;
//                    }
//                }
//            }
//        }
//        return count;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    public int twoSumSmaller(int[] nums, int left, int target) {
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode259 leetCode259 = new LeetCode259();
        leetCode259.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2);
    }
}
