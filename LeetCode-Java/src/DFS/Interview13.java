package DFS;

/**
 * 面试题13. 机器人的运动范围
 */
public class Interview13 {
    private int m;
    private int n;
    private int[] directions = new int[]{-1, 0, 1, 0, -1};
    private boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        this.m = m; this.n = n;
        visited = new boolean[m][n];
        visited[0][0] = true;
        int count = dfs(0, 0, k);
        return count;
    }

    private int dp(int m, int n, int k) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][m] = 0;
            }
        }
        dp[0][0] = 1;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 && j == 0) ||allNumsValue(i, j) > k) {
                    continue;
                }
                if (i - 1 >= 0) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] |= dp[i][j - 1];
                }
                res += dp[i][j];
            }
        }
        return res;
    }

    private int dfs(int x, int y, int k) {
        if (x == m - 1 && y == n - 1) {
            if (allNumsValue(x, y) <= k) {
                return 1;
            }
            return 0;
        }
        int count = 1;
        for (int i = 0; i < directions.length - 1; i++) {
            int newX = x + directions[i]; int newY = y + directions[i + 1];

            if (canVisited(newX, newY)) {
                if (allNumsValue(newX, newY) > k) {
                    continue;
                }
                visited[newX][newY] = true;
                count += dfs(newX, newY, k);
            }
        }
        return count;
    }

    private boolean canVisited(int x, int y) {
        return x >=0 && y >= 0 && x < m && y < n && !visited[x][y];
    }

    private int allNumsValue(int x, int y) {
        int sumOfX = 0;
        while (x != 0) {
            sumOfX += x % 10;
            x /= 10;
        }

        int sumOfY = 0;
        while (y != 0) {
            sumOfY += y % 10;
            y /= 10;
        }
        return sumOfX + sumOfY;
    }

    public static void main(String[] args) {
        Interview13 interview13 = new Interview13();
        interview13.movingCount(2, 3, 3);
    }
}
