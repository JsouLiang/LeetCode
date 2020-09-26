package Basic;

import java.util.*;

/**
 * 974. 和可被 K 整除的子数组
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 */
public class LeetCode974 {
    public int subarraysDivByK(int[] A, int K) {
        /// 前缀和数组
        Map<Integer, Integer> prevSumVals = new HashMap<>();
        prevSumVals.put(0, 1);
        int prevSum = 0;
        int count = 0;
        for (Integer num : A) {
            prevSum += num;
//            int modValue = (prevSum % K + K) % K;
            int modValue = prevSum - (prevSum / K) * K;
            int prevSameModCount = prevSumVals.getOrDefault(modValue, 0);
            count += prevSameModCount;
            prevSumVals.put(modValue, prevSameModCount + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode974 leetCode974 = new LeetCode974();
        leetCode974.subarraysDivByK(new int[]{-1, 2, 9}, 2);
    }
}
