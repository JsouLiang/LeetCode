package DoublePoint.LeetCode.easy;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 */
public class LeetCode977 {
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int left = 0, right = A.length - 1;
        int index = A.length - 1;
        while (left <= right) {
            int powLeft = A[left] * A[left];
            int powRight = A[right] * A[right];
            if ( powLeft > powRight) {
                res[index--] = powLeft;
                left++;
            } else {
                res[index--] = powRight;
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode977 leetCode977 = new LeetCode977();
        leetCode977.sortedSquares(new int[]{-4,-1,0,3,10});
        leetCode977.sortedSquares(new int[]{-7,-3,2,3,11});
        leetCode977.sortedSquares(new int[]{1, 2, 3, 4, 5});
    }
}
