package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 994. 腐烂的橘子
 */
public class LeetCode994 {
    class Point {
        int x; int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private int[][] coast;
    private int[] direction = {-1, 0, 1, 0, -1};
    public int orangesRotting(int[][] grid) {
        List<Point> badPoint = new ArrayList<>();
        coast = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                coast[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == 2) {
                    coast[i][j] = 0;
                    badPoint.add(new Point(i, j));
                }

            }
        }
        for (int i = 0; i < badPoint.size(); i++) {
            bfs(badPoint.get(i), grid);
        }
        int maxCoast = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && coast[i][j] == Integer.MAX_VALUE) {
                    return -1;
                }
                if (coast[i][j] > maxCoast && grid[i][j] != 0) {
                    maxCoast = coast[i][j];
                }
            }
        }
        return maxCoast;
    }

    private void bfs(Point start, int[][] grid) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            /// neiber
            for (int i = 0; i < direction.length - 1; i++) {
                int newX = point.x + direction[i];
                int newY = point.y + direction[i + 1];
                if (newX >= 0 && newY >= 0 && newX < coast.length && newY < coast[0].length && grid[newX][newY] == 1) {
                    if (coast[newX][newY] > coast[point.x][point.y] + 1) {
                        coast[newX][newY] = coast[point.x][point.y] + 1;
                        queue.offer(new Point(newX, newY));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode994 leetCode994 = new LeetCode994();
        leetCode994.orangesRotting(new int[][]{
                {0}
        });
        leetCode994.orangesRotting(new int[][]{
                {2,1,1},{1,1,0},{0,1,1}
        });

        leetCode994.orangesRotting(new int[][]{
                {0, 2}
        });

        leetCode994.orangesRotting(new int[][]{
                {2,1,1},{0, 1,1},{1,0, 1}
        });

    }
}
