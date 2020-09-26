package Stack.单调栈;

import java.util.Stack;

/**
 * 42. 接雨水
 */
public class LeetCode42 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        for (int index = 0; index < height.length; index++) {
            // 左边高度最大值
            int leftMaxHeight = height[index];
            for (int left = index - 1; left >= 0; left--) {
                leftMaxHeight = Math.max(leftMaxHeight, height[left]);
            }
            // 右边高度最大值
            int rightMaxHeight = height[index];
            for (int right = index + 1; right < height.length; right++) {
                rightMaxHeight = Math.max(rightMaxHeight, height[right]);
            }
            res += Math.min(leftMaxHeight, rightMaxHeight) - height[index];
        }
        return res;
    }

    public int trapDoublePoint(int[] heights) {
        int leftIndex = 0, rightIndex = heights.length - 1;
        int leftMax = heights[leftIndex], rightMax = heights[rightIndex];
        int res = 0;
        while (leftIndex < rightIndex) {
            if (leftMax <= rightMax) {
                if (heights[leftIndex] < leftMax) {
                    res += leftMax - heights[leftIndex];
                }
                leftIndex++;
                if (heights[leftIndex] > leftMax) {
                    leftMax = heights[leftIndex];
                }
            } else {
                if (heights[rightIndex] < rightMax) {
                    res += rightMax - heights[rightIndex];
                }
                rightIndex--;
                if (heights[rightIndex] > rightMax) {
                    rightMax = heights[rightIndex];
                }
            }
        }
        return res;
    }

    public int trapStack(int[] heights) {
        Stack<Integer> decreaseStack = new Stack<>();
        int waters = 0;
        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];
            while (!decreaseStack.isEmpty() && heights[decreaseStack.peek()] < currentHeight) {
                int lessIndex = decreaseStack.pop();
                if (decreaseStack.isEmpty()) {
                    break;
                }
                int lastHeighterIndex = decreaseStack.peek();
                int waterHeight = Math.min(heights[lastHeighterIndex], currentHeight) - heights[lessIndex];
                waters += (i - lastHeighterIndex - 1) * waterHeight;
            }
            decreaseStack.push(i);
        }

        return waters;
    }

    public static void main(String[] args) {
        LeetCode42 leetCode42 = new LeetCode42();
        int res = leetCode42.trapDoublePoint(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7 , 6});
        System.out.println(res);
    }
}
