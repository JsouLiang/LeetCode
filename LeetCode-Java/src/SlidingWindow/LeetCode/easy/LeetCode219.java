package SlidingWindow.LeetCode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 *
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class LeetCode219 {
    /**
     * 因为两个重复值之间的 index 差不会超过 k，所以设置一个 k 大小的 sliding window
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> windowSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            final int currentValue = nums[i];
            /// 如果 window 中已经包含当前的值，则返回 true
            if(!windowSet.add(currentValue)) {
                return true;
            }
            if (i >= k) {
                /// 保存 window 中有个 k 个数，当第 k+1 加入 window 时，将第 k + 1 - k 位置的数从 window 中移除
                windowSet.remove(nums[i - k]);
            }
        }
        return false;
    }
}
