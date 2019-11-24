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
        Arrays.sort(coins);
        return dfs(coins, new ArrayList<>(), amount);
    }

    int dfs(int[] coins, List<Integer> used, int rest) {
        if (rest == 0) {
            System.out.println(used);
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
        return min;
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        int res = leetCode322.coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(res);
    }
}
