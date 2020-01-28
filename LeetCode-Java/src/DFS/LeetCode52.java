package DFS;

public class LeetCode52 {
    private char[][] chessboard;
    private int count;

    public int totalNQueens(int n) {
        if (n == 0) {
            return 0;
        }
        count = 0;
        chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }
        dfs(0);
        return count;
    }

    private void dfs(int currentLevel) {
        if (currentLevel == chessboard.length) {
            count++;
//            System.out.println();
//            /// success
//            for (int i = 0; i < chessboard.length; i++) {
//                for (int j = 0; j < chessboard.length; j++) {
//                    System.out.print(chessboard[i][j]);
//                }
//                System.out.println();
//            }
            return;
        }

        /// check current level all position
        for (int i = 0; i < chessboard.length; i++) {
            // check diagonal
            if (checkColumn(currentLevel, i) && checkDiagonal(currentLevel, i)) {
                chessboard[currentLevel][i] = 'Q';
                dfs(currentLevel + 1);
                chessboard[currentLevel][i] = '.';
            }
        }
    }

    private boolean checkColumn(int row, int column) {
        int rowT = row - 1, columnT = column;
        while (rowT >= 0) {
            if (chessboard[rowT][columnT] == 'Q') {
                return false;
            }
            rowT--;
        }
        return true;
    }

    private boolean checkDiagonal(int row, int column) {
        int rowT = row - 1, columnT = column - 1;
        /// 左上
        while (rowT >= 0 && columnT >= 0) {
            if (chessboard[rowT][columnT] == 'Q') {
                return false;
            }
            rowT--;
            columnT--;
        }
        /// 右上
        rowT = row - 1;
        columnT = column + 1;
        while (rowT >= 0 && columnT < chessboard.length) {
            if (chessboard[rowT][columnT] == 'Q') {
                return false;
            }
            rowT--;
            columnT++;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode52 leetCode52 = new LeetCode52();
        leetCode52.totalNQueens(4);
    }
}
