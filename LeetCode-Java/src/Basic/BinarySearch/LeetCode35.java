package Basic.BinarySearch;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @implNote first bigger position
 */
public class LeetCode35 {
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                left = middle;
            } else if (nums[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
        }
        if (nums[left] == target || nums[left] > target) {
            return left;
        } else if (nums[right] == target || nums[right] > target) {
            return right;
        } else  {
            /// (nums[left] < target && target < nums[right])
            return left + 1;
        }
    }

    public static int searchInsertPosition(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        if (target< nums[0]) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
        }
        /// 查询第一个大于 target 的位置
        /// left 可能是第一次出现 target 的位置
        if (nums[left] >= target) {
            return left;
        } else if (nums[right] >= target) {
            return right;
        } else {
            return right + 1;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int res = LeetCode35.searchInsert(nums, 5);
        res = LeetCode35.searchInsert(nums, 2);
        res = LeetCode35.searchInsert(nums, 7);
        res = LeetCode35.searchInsert(nums, 0);
    }
}
