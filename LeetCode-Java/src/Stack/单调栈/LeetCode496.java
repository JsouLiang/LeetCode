package Stack.单调栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 */

public class LeetCode496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> resultMap= new HashMap<>();
        int[] res = new int[nums1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        Stack<Integer> decreaseStack = new Stack<>();
        /// 1. 先使用递减栈计算出每个数i 右边第一个大于 i 的位置
        for (int i = 0; i < nums2.length; i++) {
            while (!decreaseStack.isEmpty() && nums2[decreaseStack.peek()] < nums2[i]) {
                resultMap.put(nums2[decreaseStack.peek()], nums2[i]);
                decreaseStack.pop();
            }
            decreaseStack.push(i);
        }
        /// 2. 获取 num1 中指定元素的值
        for (int i = 0; i < nums1.length; i++) {
            if (resultMap.get(nums1[i]) != null) {
                res[i] = resultMap.get(nums1[i]);
            }

        }
        return res;
    }
}
