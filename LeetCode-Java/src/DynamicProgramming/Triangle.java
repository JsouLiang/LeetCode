package DynamicProgramming;

/**
 * 数字三角形
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * Input the following triangle:
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * Output: 11
 * Explanation: The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Input the following triangle:
 * [
 *      [2],
 *     [3,2],
 *    [6,5,7],
 *   [4,4,8,1]
 * ]
 * Output: 12
 * Explanation: The minimum path sum from top to bottom is 12 (i.e., 2 + 2 + 7 + 1 = 12).
 */
public class Triangle {

    public int minimumTotal(int[][] triangle) {
        // O(2^n)
        class TraverseSolution {
            int bestSum = Integer.MAX_VALUE;
            void traverse(int x, int y, int sum) {
                if (x == triangle.length) {
                    if (sum < bestSum) {
                        bestSum = sum;
                    }
                    return;
                }
                // 1 -> 2 -> 4 -> 8
                //
                traverse(x + 1, y, sum + triangle[x][y]);
                traverse(x + 1, y + 1, sum + triangle[x][y]);
            }
        }
        // O(2^n)
        // 自底向上递归
        class DivideConquer {
            // 从(x, y) 节点出发，到达底部的路径最短值
            int divideConquer(int x, int y) {
                if (x == triangle.length) {
                    return 0;
                }
                // (x, y) 结果依赖于 (x + 1, y) 和 (x + 1, y + 1) 的结果
                return triangle[x][y] + Math.min(divideConquer(x + 1, y), divideConquer(x + 1, y + 1));
            }
        }

        /**
         * 记忆化搜索
         * O(n^2)
         */
        class DivideConquerMemorization {
            private int[][] values = new int[][]{{Integer.MAX_VALUE}};
            int divideConquer(int x, int y) {
                if (x == triangle.length) {
                    return 0;
                }
                if (values[x][y] != Integer.MAX_VALUE) {
                    return values[x][y];
                }
                return values[x][y] = triangle[x][y] + Math.min(divideConquer(x + 1, y), divideConquer(x + 1, y + 1));
            }
        }

        class DPBottomToTop {
            int dp() {
                // dp[x][y] 表示从(x, y) 到最底部最优解
                int[][] dp = new int[triangle.length][triangle[0].length];
                // bottom value
                for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
                    dp[triangle.length - 1][i] = triangle[triangle.length - 1][i];
                }
                // bottom to top
                for (int i = triangle.length - 2; i >= 0; i--) {
                    for (int j = 0; j < i; j--) {
                        dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
                    }
                }
                return dp[0][0];
            }
        }

        class DPTopToBottom {
            int dp() {
                // dp[x][y] 表示从(0, 0) 到 (x, y) 的最优解
                int[][] dp = new int[triangle.length][triangle[0].length];
                dp[0][0] = triangle[0][0];
                // 初始化 dp 的左右边界

                for (int i = 1; i < triangle.length; i++) {
                    for (int j = 0; j < i; j++) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
                    }
                }
                int minVal = Integer.MAX_VALUE;
                for (int i = 0; i < triangle.length; i++) {
                    minVal = Math.min(minVal, dp[triangle.length - 1][i]);
                }
                return minVal;
            }
        }
        TraverseSolution traverseSolution = new TraverseSolution();
        traverseSolution.traverse(0, 0, 0);
        return traverseSolution.bestSum;
    }

}
