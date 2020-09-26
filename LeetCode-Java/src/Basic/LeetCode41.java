package Basic;

import java.util.HashSet;
import java.util.Set;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 */
public class LeetCode41 {
    // TODO:
    public int firstMissingPositive(int[] nums) {
        Set<Integer> values = new HashSet<>();
        for (int num : nums) {
            values.add(num);
        }

        for (int i = 1; ; i++) {
            if (!values.contains(i)) {
                return i;
            }
        }
    }
}
