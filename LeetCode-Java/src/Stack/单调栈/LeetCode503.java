package Stack.单调栈;

import java.util.Stack;

/**
 * 503. 下一个更大元素 II
 */
public class LeetCode503 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length];
        for (int i = 0 ; i < nums.length; i++) {
            res[i] = -1;
        }
        /// decreaseStack 中存储的是下标
        Stack<Integer> decreaseStack = new Stack<>();
        for (int i = 0; i < nums.length * 2; i++) {
            while (!decreaseStack.isEmpty() && nums[decreaseStack.peek() % nums.length] < nums[i % nums.length ]) {
                res[decreaseStack.peek() % nums.length] = nums[i % nums.length];
                decreaseStack.pop();
            }
            decreaseStack.push(i);
        }
        return res;
    }
}
