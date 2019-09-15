package DivideAndConquer;

import com.sun.nio.sctp.AbstractNotificationHandler;
import javafx.util.Pair;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 *
 * 注意:
 *
 *     给定数组的长度不会超过50000。
 *     输入数组中的所有数字都在32位整数的表示范围内。
 */
public class LeetCode493 {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return divide(nums, 0, nums.length - 1).getValue();
    }

    private Pair<int[], Integer> divide(int[] nums, int left, int right) {
        if (left == right) {
            return new Pair<>(new int[]{nums[left]}, 0);
        }
        int middle = left + (right - left) / 2;
        Pair<int[], Integer> leftSubarray = divide(nums, left, middle);
        Pair<int[], Integer> rightSubArray = divide(nums, middle + 1, right);
        Pair<int[], Integer> result = margin(leftSubarray.getKey(), rightSubArray.getKey());

        return new Pair<>(result.getKey(), leftSubarray.getValue() + rightSubArray.getValue() + result.getValue());
    }

    private Pair<int[], Integer> margin(int[] leftNums, int[] rightNums) {
        int leftPoint = 0, rightPoint = 0;
        int[] result = new int[leftNums.length + rightNums.length];
        int count = 0;

        while (leftPoint < leftNums.length && rightPoint < rightNums.length) {
            long rightValue = 2 * (long)rightNums[rightPoint];
            if (leftNums[leftPoint] > rightValue) {
                /**
                 * 当发现一个 left > 2 * right 时，为什么 count += leftNums.length - leftPoint
                 * 1. 首先，当当前的 leftPoint 值和 rightPoint 指向的值满足：left > 2 * right 时，right 应该++
                 * 2. 当满足 left > 2 * right 时，由于 leftNums 是排序好的数组，所以从 leftPoint 到 leftNums 最后一个元素均满足 left > 2 * right
                 */
                count += leftNums.length - leftPoint;
                rightPoint++;
            } else {
                leftPoint++;
            }
        }
        int index = 0;
        leftPoint = rightPoint = 0;
        while (leftPoint < leftNums.length && rightPoint < rightNums.length) {
            if (leftNums[leftPoint] < rightNums[rightPoint]) {
                result[index++] = leftNums[leftPoint++];
            } else {
                result[index++] = rightNums[rightPoint++];
            }
        }

        while (leftPoint < leftNums.length) {
            result[index++] = leftNums[leftPoint++];
        }

        while (rightPoint < rightNums.length) {
            result[index++] = rightNums[rightPoint++];
        }
        return new Pair<>(result, count);
    }

    public static void main(String[] args) {
        LeetCode493 leetCode493 = new LeetCode493();
        int res = leetCode493.reversePairs(new int[] {1,3,2,3,1});
        res = leetCode493.reversePairs(new int[] {2,4,3,5,1});
        res = leetCode493.reversePairs(new int[] {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647});

        System.out.println(res);
    }
}
