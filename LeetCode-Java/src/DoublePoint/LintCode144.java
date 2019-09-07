package DoublePoint;

/**
 * 144. 交错正负数
 * 给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
 *
 * 样例
 * 样例 1
 *
 * 输入 : [-1, -2, -3, 4, 5, 6]
 * 输出 : [-1, 5, -2, 4, -3, 6]
 * 解释 : 或者仍和满足条件的答案
 * 挑战
 * 完成题目，且不消耗额外的空间。
 */
public class LintCode144 {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 1. 先把数组按正负数区分，所有的负数在前，正数在后
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < nums.length && nums[left] < 0) {
                left++;
            }
            while (right >= 0 && nums[right] > 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        // 2. 交换正负数
//        /// 正数个数
//        int positiveCount = nums.length - right;
//        /// 负数个数
//        int negativeCount= left + 1;
//
//        // 负数 > 正数
//        if (negativeCount > positiveCount) {
//            left = 1;
//            right = nums.length - 1;
//        } else if (negativeCount < positiveCount){
//            right = nums.length - 2 ;
//            left = 0;
//        } else {
//            right = nums.length - 1;
//            left = 0;
//        }

        // 正负数个数相等
        if (nums.length % 2 == 0) {
            right = nums.length - 1;
            left = 0;
        } else if (nums[nums.length / 2] > 0) {
            // 正数多
            left = 0;
            right = nums.length - 2;
        } else {
            // 负数多
            right = nums.length - 1;
            left = 1;
        }
        // left 从负数，right 从正数，隔一个交换一次
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            right -= 2;
            left += 2;
        }
        return;
    }

    public static void main(String[] args) {
        LintCode144 lintCode144 = new LintCode144();
        lintCode144.rerange(new int[]{-1, -1, 2, 3, 4});
        lintCode144.rerange(new int[]{-1, -1, -1, 2, 3});
        lintCode144.rerange(new int[]{-1, -1, 3, 4});
        lintCode144.rerange(new int[]{-1, -2, -3, 4, 5, 6});
        lintCode144.rerange(new int[]{-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18});

    }
}
