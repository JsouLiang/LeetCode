package Basic.BinarySearch;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 */
public class LeetCode153 {
    public static int findMinUseLast(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        /// Problem：我们可以使用数组第一个数做 target，也可以使用数组最后一个数做 target
        int target = nums[nums.length - 1];
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] > target) {
                left = middle;
            } else {
                right = middle;
            }
        }

        return Math.min(nums[left], nums[right]);
    }

    public static int findMineUseFirst(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        /// 数组没有反转
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        int target = nums[0];
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                left = middle;
            } else if (nums[middle] > target) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return Math.min(nums[left], nums[right]);
    }

    public static void main(String[] args) {
        LeetCode153.findMineUseFirst(new int[]{2, 1});
        LeetCode153.findMinUseLast(new int[]{1, 2, 3, 4, 5, 6});
        LeetCode153.findMineUseFirst(new int[]{3, 4, 5, 1, 2});
        LeetCode153.findMineUseFirst(new int[]{ 7, 6, 5,4,2,1});
        LeetCode153.findMinUseLast(new int[]{ 4, 5, 6, 7, 0,  1, 2});
        LeetCode153.findMinUseLast(new int[]{ 7, 6, 5,4,2,1});
    }
}
