package BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 286. 墙与门
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * <p>
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 */
public class LeetCode286 {
    private int[] direct = {-1, 0, 1, 0, -1, 0};

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
//                    dfs(rooms, i, j, 0);
                    bfs(rooms, i, j);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int x, int y, int step) {
        for (int i = 0; i < direct.length - 1; i++) {
            int nextX = x + direct[i], nextY = y + direct[i + 1];
            // not out of bounds
            if (nextX >= 0 && nextY >= 0 && nextX < rooms.length && nextY < rooms[0].length) {
                // not hinder
                if (rooms[nextX][nextY] != -1) {
                    if (rooms[nextX][nextY] > step + 1) {
                        rooms[nextX][nextY] = step + 1;
                        dfs(rooms, nextX, nextY, step + 1);
                    }
                }
            }
        }
    }

    private void bfs(int [][] rooms, int x, int y) {
        Queue<Integer[]> pointers = new ArrayDeque<>();
        pointers.add(new Integer[]{x, y});
        while (!pointers.isEmpty()) {
            Integer[] currentPosition = pointers.poll();
            for (int i = 0; i < direct.length - 1; i++) {
                int nextX = currentPosition[0] + direct[i];
                int nextY = currentPosition[1] + direct[i + 1];
                if (nextX >= 0 && nextY >= 0 && nextX < rooms.length && nextY < rooms[0].length) {
                    int currentStep = rooms[currentPosition[0]][currentPosition[1]] + 1;
                    if (rooms[nextX][nextY] != -1 && currentStep < rooms[nextX][nextY]) {
                        rooms[nextX][nextY] = currentStep;
                        pointers.offer(new Integer[]{nextX, nextY});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode286 leetCode286 = new LeetCode286();
        int [][] board = new int[][]
                {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        leetCode286.wallsAndGates(board);
        System.out.println(board);
    }
}
