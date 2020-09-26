package DoublePoint.LeetCode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 */
public class LeetCode1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = left;
        List<List<Integer>> resInfo = new ArrayList<>();

        int currentOddCount = 0;
        int res = 0;
        while (left < nums.length && right < nums.length) {
            int currentNum = nums[right++];
            if (currentNum % 2 == 1) {
                currentOddCount++;
            }
            while (currentOddCount >= k) {
                resInfo.add(generatorResInfo(nums, left, right-1));
                res++;
                int extendRight = right;
                while (extendRight < nums.length) {
                    currentNum = nums[extendRight];
                    if (currentNum % 2 == 1) {
                        break;
                    }
                    resInfo.add(generatorResInfo(nums, left, extendRight));
                    extendRight++;
                    res++;
                }
                int leftNum = nums[left++];
                if (leftNum % 2 == 1) {
                    currentOddCount--;
                }
            }
        }
        return res;
    }


    List<Integer> generatorResInfo(int[] nums, int left, int right) {
        List<Integer> currentRes = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            currentRes.add(nums[i]);
        }
        return currentRes;
    }

    public static void main(String[] args) {
        LeetCode1248 leetCode1248 = new LeetCode1248();
        leetCode1248.numberOfSubarrays(new int[]{2,4,6}, 1);
        leetCode1248.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2);
    }
}
