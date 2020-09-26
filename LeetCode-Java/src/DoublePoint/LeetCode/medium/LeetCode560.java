package DoublePoint.LeetCode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 */
public class LeetCode560 {
    /**
     * 存在负数不能用双指针
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumError(int[] nums, int k) {
        int left = 0, right = 0;
        int subSum = 0;
        int res = 0;
        List<List<Integer>> arrayList = new ArrayList<>();
        while (right < nums.length) {
            subSum += nums[right++];
            if (subSum == k) {
                res++;
                List<Integer> values = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    values.add(nums[i]);
                }
                arrayList.add(values);
            }
           while (subSum >= k && left + 1 < right) {
               subSum -= nums[left++];
               if (subSum == k) {
                   res++;
               }
           }
        }
        return res;
    }

    public int subarraySum(int[] nums, int k) {
        int[] preSums = new int[nums.length + 1];
        preSums[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (preSums[j + 1] - preSums[i] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode560 leetCode560 = new LeetCode560();
        leetCode560.subarraySum(new int[]{-1, -1, 1}, 0);
    }
}
