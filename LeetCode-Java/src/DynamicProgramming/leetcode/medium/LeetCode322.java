package DynamicProgramming.leetcode.medium;

import DoublePoint.SlidingWindow.hard.LeetCode32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 322. Coin Change
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 */
public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
        int res = dpCoinChange(coins, amount);
        return res;
    }
    /// 超时
    int dfs(int[] coins, List<Integer> used, int rest) {
        if (rest == 0) {
            return used.size();
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin > rest) {
                continue;
            }
            used.add(coin);
            int count = dfs(coins, used, rest - coin);
            used.remove(used.size() - 1);
            min = Math.min(min, count);
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    int dfsWithRem(int[] coins, int rest, int[] record) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        if (record[rest] != 0) {
            return record[rest];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int count = dfsWithRem(coins, rest - coins[i], record);
            if (count >= 0) {
                min = Math.min(count + 1, min);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return record[rest] = min;
    }

    public int dpCoinChange(int[] coins, int amount) {
        /// dp[n] = min(dp[n - coins[0]], dp[n - coins[1]], dp[n - coins[2]]....) + 1
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int reset = 1; reset <= amount; reset++) {
            int minUsed = Integer.MAX_VALUE - 1;
            for (int coin : coins) {
                int cost = Integer.MAX_VALUE;
                if (reset - coin >= 0) {
                    cost = dp[reset - coin];;
                }
                minUsed = Math.min(minUsed, cost);
            }
            dp[reset] = minUsed + 1;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        int res = leetCode322.coinChange(new int[]{1, 2, 5}, 11);
        leetCode322.coinChange(new int[]{2}, 3);
        leetCode322.coinChange(new int[]{2}, 1);
//        System.out.println(res);
    }
}
