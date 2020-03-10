package Tree.SegmentTree.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode315 {
    /// Time out
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int greaterCount = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    greaterCount++;
                }
            }
            res.add(greaterCount);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode315 leetCode315 = new LeetCode315();
        leetCode315.countSmaller(new int[]{5,2,6,1});
    }
}
