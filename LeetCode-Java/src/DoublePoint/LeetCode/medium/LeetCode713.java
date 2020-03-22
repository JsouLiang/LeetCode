package DoublePoint.LeetCode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 713. 乘积小于K的子数组
 */
public class LeetCode713 {
    private List<List<Integer>> resRecord;

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        resRecord = new ArrayList<>();
        int count = 0;
        int res = 1;
        while (right < nums.length) {
            res *= nums[right];
            if (res < k) {
                /// 以 right 为结尾的子数组的个数：right - left + 1
                count += right - left + 1;
                recordRes(left, right, nums);
                right++;
            }
            while (res >= k) {
                res /= nums[left++];
                if (res < k) {
                    count += right - left + 1;
                    recordRes(left, right, nums);
                    right++;
                }
            }

        }
        return count;
    }

    private void recordRes(int left, int right, int[] nums) {
        /// 以 right 为结尾的，从 left ~ right 的子数组
        /// [0, 1, 2]
        /// res: [[2], [1, 2], [0, 1, 2]]
        for (int i = right; i >= left; i--) {
            List<Integer> currentRes = new ArrayList<>();
            for (int j = i; j <= right; j++) {
                currentRes.add(nums[j]);
            }
            resRecord.add(currentRes);
        }
    }


    public static void main(String[] args) {
        LeetCode713 leetCode713 = new LeetCode713();
        leetCode713.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
    }
}
