package SlidingWindowDoublePoint.LeetCode.easy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *  给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @implNote 双指针方式
 */
public class LeetCode1 {
    /// 或者使用 Java Pair
    private static class NumInfo {
        public int value;
        public int index;

        public NumInfo(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        NumInfo[] numInfos = new NumInfo[nums.length];

        for (int index = 0; index < nums.length; index++) {
            NumInfo numInfo = new NumInfo(nums[index], index);
            numInfos[index] = numInfo;
        }
        Arrays.sort(numInfos, new Comparator<NumInfo>() {
            @Override
            public int compare(NumInfo o1, NumInfo o2) {
                if (o1.value > o2.value) {
                    return 1;
                } else if (o1.value < o2.value) {
                    return -1;
                }
                return 0;
            }
        });
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (numInfos[left].value + numInfos[right].value < target) {
                left++;
            } else if (numInfos[left].value + numInfos[right].value > target) {
                right--;
            } else {
                break;
            }
        }
        return new int[]{numInfos[left].index, numInfos[right].index};
    }
    public static void main(String[] args) {
        LeetCode1.twoSum(new int[]{2, 7, 11, 15}, 9);
        LeetCode1.twoSum(new int[]{3, 2, 4}, 6);
        LeetCode1.twoSum(new int[]{3, 3}, 6);
    }
}
