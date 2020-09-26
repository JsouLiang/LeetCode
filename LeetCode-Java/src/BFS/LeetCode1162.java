package BFS;

import Basic.string.LeetCode1160;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1162. 地图分析
 */
public class LeetCode1162 {
    private int[] direction = new int[]{-1, 0, 1, 0, -1};
    class Point {
        int x; int y;

        public Point(int x, int y) {
            this(x, y, 0);
        }
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    private int[][] coast;
    public int maxDistance(int[][] grid) {
        /// 1. 找到所有的陆地
        coast = new int[grid.length][grid[0].length];
        ArrayList<Point> lands = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                coast[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == 1) {
                    lands.add(new Point(i, j));
                    coast[i][j] = 0;
                }
            }
        }
        /// 没有陆地，返回-1
        if (lands.size() == 0) {
            return -1;
        }
        for (Point point : lands) {
            bfs(point, grid);
        }
        /// find the furthest land
        Point furthestLand = null;
        int coast = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (this.coast[i][j] > coast) {
                    furthestLand = new Point(i, j);
                    coast = this.coast[i][j];
                }
            }
        }
        /// 没有海洋
        if (coast == 0) {
            return -1;
        }
        int distance = findTheNearestLand(furthestLand, grid);
        return 0;
    }

    private void bfs(Point start, int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int coast = this.coast[point.x][point.y];

            for (int i = 0; i < direction.length - 1; i++) {
                int nextX = point.x + direction[i];
                int nextY = point.y + direction[i + 1];
                if (nextX >= 0 && nextY >= 0 && nextX < grid.length && nextY < grid[0].length && !visited[nextX][nextY]) {
                    if (coast + 1 > this.coast[nextX][nextY]) {
                        continue;
                    }
                    visited[nextX][nextY] = true;
                    this.coast[nextX][nextY] = coast + 1;
                    queue.offer(new Point(nextX, nextY));
                }
            }
        }
    }

    private int findTheNearestLand(Point start, int[][] grid) {
        if (start == null) {
            return -1;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (grid[point.x][point.y] == 1) {
                return point.distance;
            }
            for (int i = 0; i < direction.length - 1; i++) {
                int nextX = point.x + direction[i];
                int nextY = point.y + direction[i + 1];
                if (nextX >= 0 && nextY >= 0 && nextX < grid.length && nextY < grid[0].length && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Point(nextX, nextY, point.distance + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode1162 leetCode1162 = new LeetCode1162();
        leetCode1162.maxDistance(new int[][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        });
        leetCode1162.maxDistance(new int[][] {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        });
        leetCode1162.maxDistance(new int[][] {
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        leetCode1162.maxDistance(new int[][] {
                {1, 1, 0},
                {0, 0, 0},
                {0, 0, 0}
        });
    }
}
