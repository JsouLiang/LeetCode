package Greedy;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 */
public class LeetCode55 {
    /**
     * TODO:
     * 从每个点计算当前点可以达到的最大位置，不断遍历数组更新该最大位置，
     * 当到数组结尾时，判断该最大是否 > 数组长度
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxPos = 0;
        /// 为什么要 <= maxPox
        /// maxPox 表示当前可达的最大位置，
        /// 数组的遍历是在可达的位置中进行遍历的，
        /// 如果在数组中间出现了 0，表示不能跳跃，这个时候 maxPox 可能始终达不到最后一个位置
        for (int i = 0; i < nums.length - 1 && i <= maxPos; i++) {
            maxPos = Math.max(nums[i] + i, maxPos);
        }
        if (maxPos >= nums.length - 1) {
            return true;
        }
        return false;
    }

    public boolean canJumpII(int[] nums) {
        int maxPosition = 0;
        int index = 0;
        while (index < nums.length) {
            /// 目前可达的最远位置在当前位置之前
            if (maxPosition < index) {
                return false;
            }
            /// 更新目前可达的最远位置
            maxPosition = Math.max(maxPosition, nums[index] + index);
            index++;
        }
        return true;
    }

    public boolean canJumpWithRecall(int[] nums) {
        return true;
    }

    /**
     * 回溯法解决该问题：
     * 递归当前节点所有的跳法，判断是否能够达到目标点
     * @param position
     * @param nums
     * @return
     */
    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        /// 跳的最远位置(不能超过数组最大长度)
        int furthestJump = Math.min(nums[position] + position, nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(position, nums)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode55 leetCode55 = new LeetCode55();
        boolean res =  leetCode55.canJump(new int[] {2,0,0,0,4});
        res =  leetCode55.canJump(new int[] {2,3,1,1,4});
        res =  leetCode55.canJump(new int[] {3,2,1,0,4});
        res =  leetCode55.canJump(new int[] {0,2,3});
        System.out.println(res);
    }
}
