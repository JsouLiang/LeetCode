package 位运算.easy;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @implNote 两个集合的 & 操作是求集合的交集
 */
public class LeetCode349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        for (int value: nums1) { nums1Set.add(value); }
        Set<Integer> nums2Set = new HashSet<>();
        for (int value: nums2) { nums2Set.add(value); }
        List<Integer> res = new LinkedList<>();
        nums1Set.retainAll(nums2Set);
        int [] output = new int[nums1Set.size()];
        int idx = 0;
        for (int s : nums1Set) output[idx++] = s;
        return output;
    }
}
