package DoublePoint.LeetCode.medium;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        int delta = 0xFFFF;
        for (int i = 0; i < nums.length; i++) {
            int standPoint = nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int currentSum = standPoint + nums[left] + nums[right];
                int currentDelta = Math.abs(currentSum - target);
                if (currentDelta < delta) {
                    delta = currentDelta;
                    result = currentSum;
                }
                /// 如果当前的总和小于 target，尝试增大下 left，看看能否是 delta 减小
                if (currentSum < target) {
                    left++;
                } else {
                    /// right 同理
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode16 test = new LeetCode16();
        test.threeSumClosest(new int[]{-1,2,1,-4}, 1);
    }
}
