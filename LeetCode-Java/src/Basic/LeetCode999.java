package Basic;

/**
 * 999. 车的可用捕获量
 */
public class LeetCode999 {
    public int numRookCaptures(char[][] board) {
        int rookX = -1, rookY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    rookX = i;
                    rookY = j;
                    break;
                }
            }
        }

        int res = helper(rookX, rookY, board);
        return res;
    }

    private int helper(int i, int j, char[][] board) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return 0;
        }
        int count = 0;
        int up = i - 1, down = i + 1, left = j - 1, right = j + 1;
        while (up >= 0 && board[up][j] != 'B') {
            if (board[up][j] == 'p') {
                count++;
                break;
            }
            up--;
        }


        while (down < board.length && board[down][j] != 'B') {
            if (board[down][j] == 'p') {
                count++;
                break;
            }

            down++;
        }

        while (left >= 0 && board[i][left] != 'B') {
            if (board[i][left] == 'p') {
                count++;
                break;
            }
            left--;
        }


        while (right < board[0].length && board[i][right] != 'B') {
            if (board[i][right] == 'p') {
                count++;
                break;
            }
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode999 leetCode999 = new LeetCode999();
        leetCode999.numRookCaptures(new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}

        });
    }
}
