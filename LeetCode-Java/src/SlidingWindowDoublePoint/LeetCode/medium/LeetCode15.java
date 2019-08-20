package SlidingWindowDoublePoint.LeetCode.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class LeetCode15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int index = 0; index < nums.length; index++) {
            // 跳过重复项
            if (index > 0 && nums[index] == nums[index-1]) {
                continue;
            }
            int left = index + 1, right = nums.length - 1;
            int target = -nums[index];

            towSum(nums, left, right, target, result);
        }
        return result;
    }

    private static void towSum(int[] nums, int left, int right, int target, List<List<Integer>> result) {
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left ++;
            } else {
                ArrayList<Integer> current = new ArrayList<>();
                current.add(-target);
                current.add(nums[left]);
                current.add(nums[right]);
                result.add(current);
                while (left < nums.length - 1 && nums[left++] == nums[left]);
                while (right > 0 && nums[right--] == nums[right]);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode15.threeSum(new int[] {-1, 0, 1, 2, -1, -4});
        LeetCode15.threeSum(new int[] {-2, 0, 0, 2, 2});
        LeetCode15.threeSum(new int[] {0, 0, 0});
    }
}
