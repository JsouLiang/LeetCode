package DynamicProgramming.leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class LeetCode174 {
    private int[][] dungeon;
    private Integer[][] mom;
    public int calculateMinimumHP(int[][] dungeon) {
        // dp[i][j] 是走到第[i, j] 个位置所需要的最小健康点
        // dp[i][j] = min(dp[i-1][j], dp[i][j-1])
        this.dungeon = dungeon;
        mom = new Integer[dungeon.length][dungeon[0].length];
        int res = dp(0, 0);

        return res;
    }

    private int dp(int i, int j) {
        if (mom[i][j] != null) {
            return mom[i][j];
        }
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            /// 终结位置如果是 > 0 的值则直接返回即可
            mom[i][j] = Math.max(1, 1 - dungeon[i][j]);
            return mom[i][j];
//            if (dungeon[i][j] > 0) {
//                return 1;
//            }
//            /// 终结位置如果 < 0 则携带的血量应该是大于它的值的
//            return 1 - dungeon[i][j];
        }
        int down = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        /// 还可以向下走，从当前位置向下走到终点所需要的最小血量
        if (i < dungeon.length - 1) {
            down = dp(i + 1, j);
        }
        /// 还可以向右走，向右走到终点所需要的最小血量
        if (j < dungeon[0].length - 1) {
            right = dp(i, j+1);
        }
        int nextValue = Math.min(right, down);
        int res = Math.max(nextValue - dungeon[i][j], 1);
        mom[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        LeetCode174 leetCode174 = new LeetCode174();
        leetCode174.calculateMinimumHP(new int[][] {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        });
    }
}
