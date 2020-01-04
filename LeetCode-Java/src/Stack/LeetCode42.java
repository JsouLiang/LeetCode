package Stack;

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
    public static void main(String[] args) {
        LeetCode42 leetCode42 = new LeetCode42();
        int res = leetCode42.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(res);
    }
}
