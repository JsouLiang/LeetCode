package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 */
public class LeetCode542 {
    private int[] direction = new int[]{-1, 0, 1, 0, -1};

    public int[][] updateMatrix(int[][] matrix) {
        int[][] distances = new int[matrix.length][matrix[0].length];
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                    for (int dir = 0; dir < direction.length - 1; dir++) {
                        int nextI = i + direction[dir]; int nextJ = j + direction[dir + 1];
                        if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n) {
                            if (matrix[nextI][nextJ] == 0) {
                                distances[i][j] = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (distances[i][j] == Integer.MAX_VALUE) {
                    distances[i][j] = bfs(i, j, m, n, matrix);
                }

            }
        }
        return distances;
    }

    class Point {
        int i;
        int j;
        int distance = 0;

        public Point(int i, int j) {
            this(i, j, 0);
        }

        public Point(int i, int j, int distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
        }
    }


    /// m * n 矩阵
    int bfs(int i, int j, int m, int n, int[][] matrix) {
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (matrix[point.i][point.j] == 0) {
                return point.distance;
            }
            for (int directionIndex = 0; directionIndex < direction.length - 1; directionIndex++) {
                int nextI = point.i + direction[directionIndex];
                int nextJ = point.j + direction[directionIndex + 1];
                if (canVisit(nextI, nextJ, m, n, visited)) {
                    visited[nextI][nextJ] = true;
                    queue.add(new Point(nextI, nextJ, point.distance + 1));
                }
            }
        }
        return -1;
    }

    boolean canVisit(int i, int j, int m, int n, boolean[][] visited) {
        if (i >= 0 && j >= 0 && i < m && j < n && !visited[i][j]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode542 leetCode542 = new LeetCode542();
        leetCode542.updateMatrix(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1},
        });
    }
}
