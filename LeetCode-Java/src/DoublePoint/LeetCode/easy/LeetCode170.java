package SlidingWindowDoublePoint.LeetCode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 170. 两数之和 III - 数据结构设计
 * 设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。
 * <p>
 * add 操作 -  对内部数据结构增加一个数。
 * find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
 * <p>
 * 示例 1:
 * <p>
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * 示例 2:
 * <p>
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */
public class LeetCode170 {
    private List<Integer> nums;
    private Map<Integer, Integer> numberInfo;

    /**
     * Initialize your data structure here.
     */
    public LeetCode170() {
        nums = new ArrayList<>();
        numberInfo = new HashMap<>();
    }

    /**
     * Add the number to an internal data structure..
     * O(n)
     *
     * @implNote 这里无法维护排序好的数组，因为无法保证数组的二分查找位置和插入数据都很快
     */
    public void add(int number) {
        nums.add(number);
        numberInfo.put(number, numberInfo.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     * O(n)
     */
    public boolean find(int value) {
        for (int num : nums) {
            int num2 = value - num;
            if (num != num2 && numberInfo.getOrDefault(num2, 0) > 0 ||
                    num == num2 && numberInfo.getOrDefault(num, 0) > 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode170 leetCode170 = new LeetCode170();
        leetCode170.add(1);
        leetCode170.add(3);
        leetCode170.add(5);
        boolean res = leetCode170.find(4);

        LeetCode170 leetCode170_2 = new LeetCode170();
        leetCode170_2.add(3);
        leetCode170_2.add(1);
        leetCode170_2.add(2);
        res = leetCode170_2.find(3);
        res = leetCode170_2.find(6);

        LeetCode170 leetCode170_3 = new LeetCode170();
        leetCode170_3.add(1);
        leetCode170_3.add(3);
        leetCode170_3.add(5);
        res = leetCode170_3.find(4);
        res = leetCode170_3.find(7);
        System.out.println(res);
    }
}
