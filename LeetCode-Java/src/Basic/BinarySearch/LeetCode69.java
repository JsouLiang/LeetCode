package Basic.BinarySearch;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class LeetCode69 {
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        long right = x / 2;
        long left = 1;
        while (left + 1< right) {
            long middle = left + (right - left) / 2;
            if (middle * middle > x) {
                right = middle;
            } else if (middle * middle < x) {
                left = middle;
            } else {
                return (int)middle;
            }
        }
        if (right * right <= x) {
            return (int)right;
        }
        return (int)left;
    }

    public static void main(String[] args) {
        int res = LeetCode69.mySqrt(1);
        res = LeetCode69.mySqrt(4);
        res = LeetCode69.mySqrt(5);
        res = LeetCode69.mySqrt(7);
        res = LeetCode69.mySqrt(8);
        res = LeetCode69.mySqrt(9);
        res = LeetCode69.mySqrt(14);
        res = LeetCode69.mySqrt(16);
        res = LeetCode69.mySqrt(1000);
        res = LeetCode69.mySqrt(99999);
    }
}
