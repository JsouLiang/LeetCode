package Basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. 缺失的区间
 */
public class LeetCode163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) {
            if (upper - lower == 0) {
                res.add("" + lower);
            } else if (upper - lower == 1) {
                res.add("" + lower);
                res.add("" + upper);
            } else {
                res.add("" + (lower) + "->" + (upper));
            }
            return res;
        }
        if (nums[0] > lower) {
            if (nums[0] - lower > 2) {
                res.add("" + (lower) + "->" + (nums[0] - 1));
            } else if (nums[0] - lower == 2) {
                res.add("" + lower);
                res.add("" + (nums[0] - 1));
            } else {
                res.add("" + lower);
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int start = nums[i];
            int end = nums[i + 1];
            if (end - start > 2) {
                res.add("" + (start+1) + "->" + (end - 1));
            } else if (end - start == 2) {
                res.add("" + (start + 1));
            }
        }
        int last = nums[nums.length - 1];
        if (last < upper) {
            if (upper - last > 2) {
                res.add("" + (last + 1) + "->" + (upper));
            } else if (upper - last == 2) {
                res.add("" + last);
                res.add("" + (upper));
            } else {
                res.add("" + upper);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode163 leetCode163 = new LeetCode163();
//        leetCode163.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99);
        leetCode163.findMissingRanges(new int[]{1, 3, 50, 75}, 0, 99);

    }
}
