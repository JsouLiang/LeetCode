package DynamicProgramming.leetcode.easy;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class LeetCode121 {
    // TODO:
    public int maxProfit(int[] prices) {
        int[] leastd = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            int j = i;
            while (j >= 0) {
                if (prices[j] < prices[i]) {
                    leastd[i] = j;
                    break;
                }
                j--;
            }
        }

        int[] dp = new int[prices.length];
        dp[0] = 0;
        dp[1] =  Math.max(0, prices[1] - prices[leastd[1]]);
        for (int i = 2; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[leastd[i]] - prices[i]);
        }
        return 0;
    }
    public static void main(String[] args) {
        LeetCode121 LeetCode121 = new LeetCode121();
//        leetCode746.minCostClimbingStairsII(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        LeetCode121.maxProfit(new int[]{7,1,5,3,6,4});
    }
}
