package DoublePoint.SlidingWindow.easy;

public class LeetCode643 {
    public double findMaxAverage(int[] nums, int k) {
        if (k > nums.length) {
            return 0.0;
        }
        int left = 0, right = 0;
        double sum = 0;
        double average = Integer.MIN_VALUE;
        while (right < nums.length) {
            if (right - left < k) {
                sum += nums[right];
                right++;
                continue;
            }
            average = Math.max(average, sum / k);
            sum -= nums[left++];
            sum+= nums[right++];
        }
        average = Math.max(average, sum/k);
        return average;
    }

    public static void main(String[] args) {
        LeetCode643 leetCode643 = new LeetCode643();
        leetCode643.findMaxAverage(new int[]{-1}, 1);
    }
}
