package dp;

/**
 * dp
 * Created by X-Liang
 * 2017-10-07-15:29
 *
 * @Description:
 */
public class HouseRobber {}

// TLE
class Solution1 {
    private int solution(int index, int[] nums) {
        if (index < 0) {
            return 0;
        }
        // 方案1：选择第 index 家店，则 index - 1家店不能偷，下一次只能去 index - 2家店
        int preceptOne = nums[index] + solution(index - 2, nums);
        // 方案2：不选择第 index 家店，下一次获取第 index - 1加家店
        int preceptTwo = solution(index - 1, nums);
        // 返回两种方案的最大值
        return Math.max(preceptOne, preceptTwo);
    }

    public int rob(int[] nums) {
        return solution(nums.length - 1, nums);
    }
}
// AC  O(n)
// 一共 n 个状态，每个状态计算一次
class Solution2 {
    // 保存计算过的解
    private static int[] results;
    // 记忆化搜索
    private int solution(int index, int[] nums) {
        if (index < 0) {
            return 0;
        }

        if (results[index] >= 0) {
            return results[index];
        }

        // 方案1：选择第 index 家店，则 index - 1家店不能偷，下一次只能去 index - 2家店
        // nums[index] + solution(index - 2, nums): 选择 index 时会有 index - 1的后效性，所以这里用 index - 2 解除后效性
        int preceptOne = nums[index] + solution(index - 2, nums);
        // 方案2：不选择第 index 家店，下一次获取第 index - 1加家店
        int preceptTwo = solution(index - 1, nums);
        // 返回两种方案的最大值
        results[index] = Math.max(preceptOne, preceptTwo);

        return results[index];
    }

    public int rob(int[] nums) {
        results = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            results[i] = -1;
        }
        return solution(nums.length - 1, nums);
    }
}

// DP
class Solution3 {
    // 保存计算过的解
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] results = new int[nums.length];
        results[0] = nums[0];
        results[1] = Math.max(nums[0], nums[1]);
        // max(选第 i 个物品 + 前 i-2得到的值，不选第 i 个物品的话就是前 i-1个物品得到的值)
        // results[i] = max(results[i - 2] + nums[i], results[i - 1])
        for (int i = 2; i < nums.length; i++) {
            results[i] = Math.max(results[i - 2] + nums[i], results[i - 1]);
        }
        return results[nums.length - 1];
    }
}
