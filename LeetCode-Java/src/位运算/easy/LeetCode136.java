package 位运算.easy;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @implNote 核心思想：
 * n ^ n = 0
 */
public class LeetCode136 {
    public static int singleNumber(int[] nums) {
        int value = nums[0];

        for (int i = 1; i < nums.length;i++) {
            value = value ^ nums[i];
        }
        return value;
    }
    public static void main(String[] args) {
        int res = LeetCode136.singleNumber(new int[]{4,1,2,1,2});
        System.out.println(res);
    }

}
