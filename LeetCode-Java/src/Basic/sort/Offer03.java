package Basic.sort;

/**
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Offer03 {
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // num[0] = 2
            // num[0] = 2 应该将2放到 num[2] 位置
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                // swap
                int tem = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tem;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Offer03 offer03 = new Offer03();
        int res = offer03.findRepeatNumber(new int[]{2, 3, 1, 4, 3});
        System.out.println(res);
    }
}
