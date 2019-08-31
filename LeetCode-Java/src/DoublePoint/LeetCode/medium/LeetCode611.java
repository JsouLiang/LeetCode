package SlidingWindowDoublePoint.LeetCode.medium;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 *
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 *
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 */
public class LeetCode611 {
    public static int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int right = nums.length - 1;
        int result = 0;
        for (int index = 0; index < nums.length; index++) {
            int targetIndex = nums.length - 1 - index;
            /// 选从最大的边(从右开始)当做 target 开始选择，从最小的位置(从左开始)选择一条边加上小于 target 的第一条边开始算
            /// 如果此时能够构成三角形，那么大于当前最小边的位置 与 小于 target 的第一条边都能组成三角形
            result += findLegalThreeEdge(nums, 0 , targetIndex - 1, nums[targetIndex]);
        }
        return result;
    }

    private static int findLegalThreeEdge(int[] nums, int left, int right, int target) {
        int count = 0;
        while (left < right) {
            /// 三角形：两边之和大于第三边
            /// 如果 left + right > target 则说明 > left 的边 + right 都可以与 target 组成三角形
            if (nums[left] + nums[right] > target) {
                count += right - left;
                right--;
            } else {
                /// 两边之和小于第三边，则增加下最短的边，left++，查看下第一个大于当前边的边是否与 right，target 组成三角形
                left++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode611.triangleNumber(new int[]{2,2,3,4});
    }
}
