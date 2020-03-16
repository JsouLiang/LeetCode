package DFS;

import java.util.ArrayList;
import java.util.List;

public class LeetCode351 {
    private int[][] map = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
    };
    private boolean[][] visited;

    public int numberOfPatterns(int m, int n) {
        visited = new boolean[3][3];
        List<List<Integer>> paths = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int pointCount = m; pointCount <= n; pointCount++) {
                    visited[i][j] = true;
                    List<Integer> currentPath = new ArrayList<>();
                    currentPath.add(map[i][j]);
                    count += dfs(i, j, 1, pointCount, currentPath, paths);
                    visited[i][j] = false;
                }
            }
        }
        return count;
    }

    private int dfs(int i, int j, int currentCount, int pointCount, List<Integer> currentPath, List<List<Integer>> paths) {
        if (currentCount >= pointCount) {
            paths.add(new ArrayList(currentPath));
            System.out.println(currentPath);
            return 1;
        }
        int count = 0;
        for (int newi = 0; newi < 3; newi++) {
            for (int newj = 0; newj < 3; newj++) {
                if (canVisited(i, j, newi, newj)) {
                    visited[newi][newj] = true;
                    currentPath.add(map[newi][newj]);
                    count += dfs(newi, newj, currentCount + 1, pointCount, currentPath, paths);
                    currentPath.remove(currentPath.size() - 1);
                    visited[newi][newj] = false;
                }
            }
        }
        return count;
    }

    private boolean canVisited(int i, int j, int newi, int newj) {
        /// 如果访问过不能在访问
        if (visited[newi][newj]) {
            return false;
        }
        /// [newi, newj] 与[i, j] 紧邻
        if (Math.abs(newi - i) <= 1 && Math.abs(newj - j) <= 1) {
            return true;
        }
        // [newi, newj] 与[i, j] 中间隔着点
        if (Math.abs(newi - i) != 1 && Math.abs(newj - j) != 1) {
            /// 如果隔着的点访问过则可以访问[newi, newj]
            return visited[(newi + i) / 2][(newj + j) / 2];
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode351 leetCode351 = new LeetCode351();
        leetCode351.numberOfPatterns(3, 3);
    }
}
