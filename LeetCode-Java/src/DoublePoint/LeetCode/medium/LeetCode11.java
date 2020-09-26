package DoublePoint.LeetCode.medium;

public class LeetCode11 {
    class BoundInfo {
        int height;
        int index;

        public BoundInfo(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    public int maxArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        int left = 0, right = heights.length - 1;
        BoundInfo leftMax = new BoundInfo(heights[left], left), rightMax = new BoundInfo(heights[right], right);
        int res = Integer.MIN_VALUE;
        while (left < right) {
            int currentRes = calculateSize(leftMax, rightMax);
            res = Math.max(currentRes, res);
            if (heights[left] < heights[right]) {
                left++;
                if (heights[left] > leftMax.height) {
                    leftMax.height = heights[left];
                    leftMax.index = left;
                }
            } else {
                right--;
                if (heights[right] > rightMax.height) {
                    rightMax.height = heights[right];
                    rightMax.index = right;
                }
            }
        }
        return res;
    }

    private int calculateSize(BoundInfo left, BoundInfo right) {
        int height = Math.min(left.height, right.height);
        int width = right.index - left.index;
        return height * width;
    }

    public static void main(String[] args) {
        LeetCode11 leetCode11 = new LeetCode11();
        leetCode11.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
    }
}
