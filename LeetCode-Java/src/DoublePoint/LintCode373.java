package DoublePoint;

/**
 * 373. 奇偶分割数组
 * 分割一个整数数组，使得奇数在前偶数在后。
 *
 * 样例
 * 样例1:
 *
 * 输入: [1,2,3,4]
 * 输出: [1,3,2,4]
 * 样例2:
 *
 * 输入: [1,4,2,3,5,6]
 * 输出: [1,3,5,4,2,6]
 * 挑战
 * 在原数组中完成，不使用额外空间。
 */
public class LintCode373 {
    public void partitionArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 奇数
            while (left < nums.length && nums[left] % 2 == 1) {
                left++;
            }
            // 偶数
            while (right >= 0 && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] =  temp;
                left++; right--;
            }
        }
        return;
    }

    // LeetCode 905
    public int[] sortArrayByParity(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 偶数
            while (left < nums.length && nums[left] % 2 == 0) {
                left++;
            }
            // 奇数
            while (right >= 0 && nums[right] % 2 == 1) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] =  temp;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        LintCode373 lintCode373 = new LintCode373();
        lintCode373.partitionArray(new int[]{1,4,2,3,5,6});
    }
}
