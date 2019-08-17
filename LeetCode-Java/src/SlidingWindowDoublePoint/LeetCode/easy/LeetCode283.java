package SlidingWindowDoublePoint.LeetCode.easy;

import Basic.string.LeetCode28;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * @implNote 同向双指针
 */
public class LeetCode283 {
    public static void moveZeroes(int[] nums) {
        /// i 指向最后一个非零的位置，j 则是遍历 nums 的指针，
        /// 如果 nums[j] 非零，就把 nums[j]放到 i 位置，并且 i++
        /// 如果 nums[j] 零，则j 继续往后遍历，i 不变
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
            j++;
        }
        while (i < j) {
            nums[i++] = 0;
        }
        System.out.println(nums);
    }

    public static void main(String[] args) {
        LeetCode283.moveZeroes(new int[] {0,1,0,3,12});
    }
}
