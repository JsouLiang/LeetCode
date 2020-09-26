package DFS;

import java.util.HashSet;
import java.util.Set;

public class LeetCode36 {
    char[][] board;
    int column = 9;
    int row = 9;
    public boolean isValidSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                char currentC = board[i][j];
                if (isNum(currentC)) {
                    if (!(isValidCell(j, i) && isValidColumn(j, i) && isValidRow(j, i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidColumn(int currentRow, int currentColumn) {
        Set<Character> visited = new HashSet<>();
        for (int i = currentColumn; i < column; i++) {
            char currentChar = board[i][currentRow];
            if (visited.contains(currentChar)) {
                return false;
            }
            if (isNum(currentChar)) {
                visited.add(currentChar);
            }
        }
        return true;
    }

    private boolean isValidRow(int currentRow, int currentColumn) {
        Set<Character> visited = new HashSet<>();
        for (int i = currentRow; i < row; i++) {
            char currentChar = board[currentColumn][i];
            if (visited.contains(currentChar)) {
                return false;
            }
            if (isNum(currentChar)) {
                visited.add(currentChar);
            }
        }
        return true;
    }

    private boolean isValidCell(int currentRow, int currentColumn) {
        int cellRow = currentRow / 3;
        int cellColumn = currentColumn / 3;
        Set<Character> visited = new HashSet<>();
        int startRow = 3 * cellRow; int startColumn = 3 * cellColumn;
        for (int i = startColumn; i < startColumn + 3; i++) {
            for (int j = startRow; j < startRow + 3; j++) {
                char currentChar = board[i][j];
                if (visited.contains(currentChar)) {
                    return false;
                }
                if (isNum(currentChar)) {
                    visited.add(currentChar);
                }
            }
        }
        return true;
    }

    boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        LeetCode36 leetCode36 = new LeetCode36();
        leetCode36.isValidSudoku(new char[][]{
                {'.','4','.','.','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'},
                {'.','.','.','1','.','.','7','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                    {'.','.','.','3','.','.','.','6','.'},
                        {'.','.','.','.','.','6','.','9','.'},
                            {'.','.','.','.','1','.','.','.','.'},
                                {'.','.','.','.','.','.','2','.','.'},
                                    {'.','.','.','8','.','.','.','.','.'}
        });
    }
}
