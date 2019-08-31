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

    /**
     * 给一整数数组, 找到数组中有多少组 不同的元素对 有相同的和, 且和为给出的 target 值, 返回对数.
     * @implNote 如果先去重呢？ [7 , 7]  target: 14 => [7] 还需要做别的判断是否满足
     * @param nums [1, 1, 2, 45, 46, 46]
     * @param target 47
     * @return 2 : 1 + 46 = 47; 2 + 25 = 27
     */
    public static int twoSumWithUniquePair(int[] nums, int target) {
        return 0;
        //TODO;
    }

    public static void main(String[] args) {
        LeetCode1.twoSum(new int[]{2, 7, 11, 15}, 9);
        LeetCode1.twoSum(new int[]{3, 2, 4}, 6);
        LeetCode1.twoSum(new int[]{3, 3}, 6);
    }
}
