package DynamicProgramming.leetcode.easy;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 */
public class LeetCode303 {
    static class NumArray {
        private int[] data;
        public NumArray(int[] nums) {
            data = nums;
        }

        public int sumRange(int i, int j) {
            if (j == i) {
                return data[i];
            }
            return data[j] + sumRange(i, j-1);
        }
    }

    static class NumArrayDP {
        private int[] sum;

        /**
         * sum[i] = nums[0] + nums[1] + nums[2] + .... nums[i-1]
         * sum[1] = num[0]
         * sum[2] = sum[1] + num[1]
         *
         * sum[i, j] = sum[j + 1] - sum[i]
         */
        public NumArrayDP(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length;i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        int res = numArray.sumRange(0, 2);

        NumArrayDP numArrayDP = new NumArrayDP(new int[]{-2,0,3,-5,2,-1});
        int resDP = numArray.sumRange(0, 2);
        resDP = numArray.sumRange(2, 5);
        resDP = numArray.sumRange(0, 5);
        System.out.println("----");
    }
}



