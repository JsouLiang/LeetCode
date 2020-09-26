package Basic;

public class LeetCode283 {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int res = 1;
            for (int j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }
                res *= nums[j];
            }
            output[i] = res;
        }
        return output;
    }

    public int[] productExceptSelfII(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int[] preMu = new int[nums.length];
        int[] backMu = new int[nums.length];
        int[] output = new int[nums.length];
        preMu[0] = nums[0]; backMu[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            preMu[i] = preMu[i - 1] * nums[i];
            backMu[nums.length - i - 1] = backMu[nums.length - i] * nums[nums.length - i - 1];
        }
        output[0] = backMu[1];
        output[nums.length - 1] = preMu[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            output[i] = preMu[i - 1] * backMu[i + 1];
        }
        return output;
    }

    public static void main(String[] args) {
        LeetCode283 leetCode283 = new LeetCode283();
        leetCode283.productExceptSelfII(new int[]{1});
    }
}
