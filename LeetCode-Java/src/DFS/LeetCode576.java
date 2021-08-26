package DFS;

/**
 * 576. 出界的路径数
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * <p>
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 * <p>
 */
public class LeetCode576 {
    private int maxColumn;
    private int maxRow;
    private int maxStep;
    boolean[][] visited = new boolean[50][50];

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        maxRow = m;
        maxColumn = n;
        maxStep = maxMove;
        int res = findPaths(startRow, startColumn, 0);
        return res;
    }

    private int findPaths(int currentRow, int currentColumn, int steps) {
        if (OutBound(currentRow, currentColumn)) {
            return 0;
        }
        int topPaths = findNext(currentRow-1, currentColumn, steps);
        int bottomPaths = findNext(currentRow + 1, currentColumn, steps);
        int leftPaths = findNext(currentRow, currentColumn - 1, steps);
        int rightPaths = findNext(currentRow, currentColumn + 1, steps);

        return topPaths + bottomPaths + leftPaths + rightPaths;
    }

    int findNext(int row, int column, int steps) {
        if (steps > maxStep) {
            return 0;
        }
        if (OutBound(row, column)) {
            return 1;
        }
        int paths = findPaths(row, column, steps + 1);

        return paths;
    }


    private boolean InBound(int row, int column) {
        if (row == 0 || column == 0) {
            return true;
        }
        if (row == maxRow - 1 || column == maxColumn - 1) {
            return true;
        }
        return false;
    }

    private boolean OutBound(int row, int column) {
        if (row < 0 || column < 0) {
            return true;
        }
        if (row >= maxRow || column >= maxColumn) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        LeetCode576 leetCode576 = new LeetCode576();
        leetCode576.findPaths(2, 2, 2, 0, 0);
    }

}
