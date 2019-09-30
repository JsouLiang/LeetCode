package BFS;

import java.util.*;

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
            return y == 0 || x == 0 || y == board.length - 1 || x == board[0].length - 1;
        }

        boolean inBoard(char[][] board) {
            return 0 <= y && y < board.length && 0 <= x && x < board[0].length;
        }
    }

    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        if (board.length == 0) {
            return;
        }
        final int row = board.length;
        final int column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == O) {
                    if (bfs(board, new Point(j, i))) {
                        board[i][j] = X;
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
        visitedMap[currentPoint.y][currentPoint.x] = true;
        List<Point> visitedPoint = new LinkedList<>();

        while (!queue.isEmpty()) {
            final Point point = queue.poll();
            visitedPoint.add(point);

            if (point.isBoundOfBoard(board)) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                Point nextPoint = new Point(point.x + dx[i], point.y + dy[i]);
                if (nextPoint.inBoard(board) && board[nextPoint.y][nextPoint.x] == O && visitedMap[nextPoint.y][nextPoint.x]  == false) {
                    visitedMap[nextPoint.y][nextPoint.x] = true;
                    queue.offer(nextPoint);
                }
            }
        }
        for (Point point : visitedPoint) {
            board[point.y][point.x] = O;
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

        leetCode130.solve(new char[][]{
                {'X',  'O' , 'X'},
                {'O',  'X' , 'O'},
                {'X',  'O' , 'X'},
        });
        leetCode130.solve(new char[][]{
                {'O' , 'O'},
                {'O' , 'O'},
        });
    }
}
