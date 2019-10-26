package DoublePoint.LeetCode.medium;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int position = quickSelect(nums, left, right);
            if (position > k - 1) {
                right = position - 1;
            } else if (position < k - 1){
                left = position + 1;
            } else {
                return nums[position];
            }
        }
        return nums[k - 1];
    }

    private int quickSelect(int[] nums, int left, int right) {
        int start = left;
        int point = nums[left];
        /// 左边的元素 大于 point
        /// 右边的元素 小于 point
        left += 1;
        while (left < right) {
            while (left < nums.length && nums[left] >= point) {
                left++;
            }
            while (right >= 0 && nums[right] < point) {
                right--;
            }
            if (left < right) {
                swipe(nums, left, right);
//                left++; right--;
            }
        }
        // 结束循环后，right 可能指向大于 point 的元素，如果满足这个条件，那么 right 的位置就是 point 应该在的位置
        if (nums[right] > point) {
            swipe(nums, start, right);
        }
        return right;
    }

    private void swipe(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        int res = leetCode215.findKthLargest(new int[]{3,2,1,5,6}, 2);
        res = leetCode215.findKthLargest(new int[]{2, 1}, 1);

        res = leetCode215.findKthLargest(new int[]{3, 3, 1,2,6,4}, 1);
        res = leetCode215.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        res = leetCode215.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 2);

    }
}
