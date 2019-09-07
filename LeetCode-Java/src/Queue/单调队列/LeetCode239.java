package Queue.单调队列;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class LeetCode239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        ArrayList<Integer> res = new ArrayList<>();
        /// queue 是一个单调递减队列，队列中存放的是下标 index
        /// 因为队列时单调递减的，所以当前队列中队首元素一定是当前滑动窗口的最大值
        /// 为确保队列的递减性，我们要在元素入队时对元素进行检查：
        /// 如果元素 小于队尾元素则直接入队
        /// 如果元素 大于队尾元素则将队尾元素出队，再将新元素入队
        Deque<Integer> queue = new ArrayDeque<>();

        int[] result = new int[nums.length - k + 1];

        for (int index = 0; index < nums.length; index++) {
            /// 队首元素已经不再窗口之内了
            /// 注意：
            /// 1. index 从 0 开始，当 index == k - 1 时此时队列中元素个数恰好达到 k 的长度
            /// 2. index - k + 1 应该是当前状态下滑动窗口的队首的起点：
            /// 比如 当前 index = 5，k = 3 的话，当前队列的起始 index 应该是 >= 3的，[3，4，5]
            if (index > k - 1 && queue.peekFirst() < index - k + 1) {
                queue.pollFirst();
            }
            /// 维持队列的单调性
            /// 在元素入队时，比较队尾元素，如果队尾元素小于即将入队的元素，则将队尾元素移除
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[index]) {
                queue.pollLast();
            }
            queue.offerLast(index);
            /// 注意 index 是从0 开始的，index + 1 == k 时就到达第一个窗口，输出最大值即可
            if (index + 1 >= k) {
                result[index + 1 - k ] = nums[queue.peekFirst()];
            }
        }
//        int[] result = new int[res.size()];
//        for (int i = 0; i < res.size(); i++) {
//            result[i] = res.get(i);
//        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode239.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        LeetCode239.maxSlidingWindow(new int[]{7,2,4}, 2);
    }
}
