package Basic.BinarySearch;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * @implNote find first position of target
 */
public class LeetCode34 {
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                end = middle;
            } else if (nums[middle] > target) {
                end = middle;
            } else {
                start = middle;
            }
        }
        /// {2, 2, 2}
        /// 循环结束后，start = 0， end = 1
        int[] res = new int[2];
        int i;
        if (nums[start] == target) {
            i = start;
            res[0] = start;
        } else if (nums[end] == target) {
            i = end;
            res[0] = end;
        } else {
            return new int[]{-1, -1};
        }
        while (i < nums.length && nums[i] == target) {
            i++;
        }
        res[1] = i - 1;
        return res;

    }
    public static void main(String[] args) {
//        int[] res = LeetCode34.searchRange(new int[]{1}, 1);
        int[] res = LeetCode34.searchRange(new int[]{5,7,7,8,8,10}, 6);
        res = LeetCode34.searchRange(new int[]{2, 2}, 2);
        res = LeetCode34.searchRange(new int[] {5,7,7,8,8,10},8);
        System.out.println(res);
    }
}
