package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 */
public class LeetCode51 {
    private char[][] chessboard;

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(0, res);
        return res;
    }

    private void dfs(int currentLevel, List<List<String>> res) {
        if (currentLevel == chessboard.length) {
            List<String> rowInfo = new ArrayList<>();
            for (int i = 0; i < chessboard.length; i++) {
                String chessboardStr = String.valueOf(chessboard[i]);
                rowInfo.add(chessboardStr);
            }
            res.add(rowInfo);
            return;
        }

        /// check current level all position
        for (int i = 0; i < chessboard.length; i++) {
            // check diagonal
            if (checkColumn(currentLevel, i) && checkDiagonal(currentLevel, i)) {
                chessboard[currentLevel][i] = 'Q';
                dfs(currentLevel + 1, res);
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
        LeetCode51 leetCode51 = new LeetCode51();
        leetCode51.solveNQueens(4);
    }
}
