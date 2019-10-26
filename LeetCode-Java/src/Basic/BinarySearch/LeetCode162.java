package Basic.BinarySearch;

import DoublePoint.LeetCode.medium.LeetCode16;

/**
 * Find Peak Element
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 *
 */
public class LeetCode162 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0; int right = nums.length - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            int middleLeft = (middle - 1) > 0 ? middle - 1 : 0;
            int middleRight = (middle + 1) >= nums.length ? nums.length : middle + 1;
            // 1 < 2 < 3 峰值在 middle 右边
            if (nums[middleLeft] < nums[middle]  && nums[middle] <nums[middleRight]) {
                left = middleRight;
            } else if (nums[middleLeft] > nums[middle] && nums[middle] > nums[middleRight]) {
                // 3 > 2 > 1 峰值在 middle 左边
                right = middleLeft;
            } else if (nums[middleLeft] > nums[middle] && nums[middleRight] > nums[middle]) {
                // 3 > 2,  2 < 4 middle 正好是个低谷
                right = middleLeft;
            } else {
                return middle;
            }
        }
        if (nums[left] < nums[right]) {
            return right;
        }
        return left;
    }

    public static void main(String[] args) {
        LeetCode162 leetCode162 = new LeetCode162();
        int res = leetCode162.findPeakElement(new int[]{1,2,3,1});
        res = leetCode162.findPeakElement(new int[]{1,2,1,3,5,6,4});
        System.out.println(res);

    }
}
