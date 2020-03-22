package Tree.LeetCode;

import java.util.Stack;

/**
 * 255. 验证前序遍历序列二叉搜索树
 */
public class LeetCode255 {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int currentMaxValue = Integer.MIN_VALUE;
        for (int value : preorder) {
            if (value < currentMaxValue) {
                return false;
            }
            /// 找到最后一个比 val 小的元素，这个值即为 val 的根节点，同时记录该根节点值，后面的值不能比该值小
            while (!stack.isEmpty() && value > stack.peek()) {
                currentMaxValue = stack.pop();
            }
            stack.push(value);
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode255 leetCode255 = new LeetCode255();
//        leetCode255.verifyPreorder(new int[]{5, 2, 6, 1, 3});
//        leetCode255.verifyPreorder(new int[]{5, 2, 1, 3, 6});
        leetCode255.verifyPreorder(new int[]{10, 8, 6, 4, 3, 1, 5, 7, 9, 14, 11});
        leetCode255.verifyPreorder(new int[]{10, 8, 6, 5, 3, 1, 5, 7, 9, 14, 11});
    }
}
