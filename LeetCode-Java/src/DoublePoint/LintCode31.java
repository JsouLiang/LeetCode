package DoublePoint;

/**
 * 31. 数组划分
 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
 *
 * 所有小于k的元素移到左边
 * 所有大于等于k的元素移到右边
 * 返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
 *
 * 样例
 * 例1:
 *
 * 输入:
 * [],9
 * 输出:
 * 0
 *
 * 例2:
 *
 * 输入:
 * [3,2,2,1],2
 * 输出:1
 * 解释:
 * 真实的数组为[1,2,2,3].所以返回 1
 */
public class LintCode31 {
    /**
     * @param nums: The integer array you should partition
     * @param target: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < nums.length && nums[left] < target) {
                left++;
            }
            while (right >=0 && nums[right] >= target) {
                right--;
            }
            if (left < right) {
                swipe(nums, left, right);
            }

        }
        return left;
    }

    private void swipe(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        LintCode31 lintCode31 = new LintCode31();
        lintCode31.partitionArray(new int[]{3,2,2,1}, 2);

        lintCode31.partitionArray(new int[]{7,7,9,8,6,6,8,7,9,8,6,6}, 10);

    }
}
