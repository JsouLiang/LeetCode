package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 */
public class LeetCode130 {
    // 上，下，左，右
    static final int[] dx = {0 , 0, -1, 1};
    static final int[] dy = {-1, 1,  0, 0};
    final char X = 'X';
    final char O = 'O';
    class Point {
        int x; int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isBoundOfBoard(char[][] board) {
            return y == board.length - 1 || x == board[0].length - 1;
        }

        boolean inBoard(char[][] board) {
            return y < board.length && x < board[0].length;
        }
    }

    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        final int row = board.length;
        final int column = board[0].length;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (board[x][y] == O) {
                    if (bfs(board, new Point(x, y))) {
                        board[x][y] = X;
                    }
                }
            }
        }
        return;
    }

    /// 判断当前点是否能够连接到边界
    private boolean bfs(char[][] board, Point currentPoint) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(currentPoint);
        boolean[][] visitedMap = new boolean[board.length][board[0].length];
        visitedMap[currentPoint.x][currentPoint.y] = true;

        while (!queue.isEmpty()) {
            final Point point = queue.poll();
            if (point.isBoundOfBoard(board)) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                Point nextPoint = new Point(point.x + dx[i], point.y + dy[i]);
                if (nextPoint.inBoard(board) && board[nextPoint.x][nextPoint.y] == O && visitedMap[nextPoint.x][nextPoint.y]  == false) {
                    visitedMap[nextPoint.x][nextPoint.y] = true;
                    queue.offer(nextPoint);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode130 leetCode130 = new LeetCode130();
        leetCode130.solve(new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
        });
    }
}
