package Basic.BinarySearch;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 *
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 *
 * 提示：
 *
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class LeetCode704 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        /// [left, right) 半闭半开区间
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                right = middle;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static int searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        /// 结局 left 与 right 相差一个数
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                left = middle;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1,0,3,5,9,12};
        int result = LeetCode704.search(array, 9);
        array = new int[]{-1,0,3,5,9,12};
        result = LeetCode704.search(array, 2);
        System.out.println(result);
    }
}
