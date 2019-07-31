package dp;

/**
 * DP
 * Created by X-Liang
 * 2017-10-07-19:02
 *
 * @Description: N*M的棋盘上，小兵要从左下角走到右上角，只能向上或者向右走， 问有多少种走法
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 *  [0,0,0]，
 *  [0,1,0],
 *  [0,0,0]
 * ]
 *  The total number of unique paths is 2.
 *  给你一个棋盘，0 代表可走，1代表障碍，求从左上角到右下角可走步数
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = 0;
        if (m >= 1) {
            n = obstacleGrid[0].length;
        } else {
            return 0;
        }

        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] result = new int[m][n];
        result[m - 1][n - 1] = 1;

        for (int i = m - 2; i >= 0; i--) {
            if (obstacleGrid[i][n - 1] == 1) {
                result[i][n - 1] = 0;
            } else {
                result[i][n - 1] = result[i + 1][n - 1];
            }

        }

        for (int i = n - 2; i >= 0; i--) {
            if (obstacleGrid[m - 1][i]  == 1) {
                result[m - 1][i] = 0;
            } else {
                result[m - 1][i] = result[m - 1][i + 1];
            }

        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = result[i + 1][j] + result[i][j + 1];
                }
            }
        }
        return result[0][0];
    }


    // 记忆化搜索：有Error，需要修改
    class ToDo {
        private int[][] maps;
        private int[][] results;

        int f(int m, int n) {
            if (m < 0 || n < 0) {
                return 0;
            }

            if (m == 0 || n == 0) {
                return maps[m][n] == 0 ? 1 : 0;
            }

            if (results[m][n] != -1) {
                return results[m][n];
            }

            if (maps[m][n] == 1) {
                results[m][n] = 0;
            } else {
                results[m][n] = f(m - 1, n) + f(m, n - 1);
            }

            return results[m][n];
        }

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = 0;
            if (m >= 1) {
                n = obstacleGrid[0].length;
            } else {
                return 0;
            }
            maps = obstacleGrid;

            results = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    results[i][j] = -1;
                }
            }

            return f(m - 1, n - 1);
        }
    }
}
