package DoublePoint.LeetCode.medium;

public class LeetCode287 {
    public int findDuplicate(int[] nums) {
        /// nums[i] 的值表示 i 节点的下一个节点，比如 nums[1] = 3 即 1 -> 3 相连
        ///  0, 1, 2, 3, 4
        /// [1, 3, 4, 2, 2]
        /// 0 -> 1 -> 3 -> 2 -> 4
        ///                ^    |
        ///                |____|
        /// 快慢指针相遇
        int fast = 0, slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast != slow);
        /// 找到环的入口
        slow = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    public static void main(String[] args) {
        LeetCode287 leetCode287 = new LeetCode287();
        leetCode287.findDuplicate(new int[]{3, 1, 3, 4, 2});
    }
}
