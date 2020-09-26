package DynamicProgramming.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.11. 硬币
 *硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 *
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 */
public class Interview0811 {
    private int[] coins = new int[]{25, 10, 5, 1};
    Integer[][] visited;
    public int waysToChange(int n) {
        List<List<Integer>> all = new ArrayList<>();
        List<Integer> current = new ArrayList<>();;
        visited = new Integer[n + 1][coins.length];
        int count = dfs(n, 0, all, current);
        // TODO: DP
        return count;
    }

    private int dfs(int currentSum, int index, List<List<Integer>> all, List<Integer> currentRes) {
        if (currentSum == 0) {
            all.add(new ArrayList<>(currentRes));
            return 1;
        }
        if (visited[currentSum][index] != null) {
            return visited[currentSum][index];
        }
        int count = 0;
        for (int i = index; i < coins.length; i++) {
            if (currentSum - coins[i] >= 0) {
                currentRes.add(coins[i]);
                count += dfs(currentSum - coins[i], i, all, currentRes);
                currentRes.remove(currentRes.size() - 1);
            }

        }
        visited[currentSum][index] = count % 1000000007;
        return visited[currentSum][index];
    }

    public static void main(String[] args) {
        Interview0811 interview0811 = new Interview0811();
        interview0811.waysToChange(10);
    }
}
