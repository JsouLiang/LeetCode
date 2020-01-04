package Stack.单调栈;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 */

public class LeetCode84 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] newHeight = new int[heights.length + 1];
        for (int i = 0; i < heights.length; i++) {
            newHeight[i] = heights[i];
        }
        newHeight[heights.length] = 0;

        Stack<Integer> increaseStack = new Stack<>();
        int size = 0;
        for (int i = 0; i < newHeight.length; i++) {
            while (!increaseStack.isEmpty() && newHeight[increaseStack.peek()] > newHeight[i]) {
                int popedIndex = increaseStack.pop();
                int currentHeight = newHeight[popedIndex];
                int width = i - popedIndex;


                int currentSize = currentHeight * width;
                size = Math.max(currentSize, size);
            }
            increaseStack.push(i);
        }
        return size;
    }

    public static void main(String[] args) {
        LeetCode84 leetCode84 = new LeetCode84();
        leetCode84.largestRectangleArea(new int[]{2, 1, 2});

        leetCode84.largestRectangleArea(new int[]{2});
        leetCode84.largestRectangleArea(new int[]{2,1,5,6,2,3});
    }
}
