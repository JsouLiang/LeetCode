package DFS;

public class LeetCode531 {
    public int findLonelyPixel(char[][] picture) {
        int lonelyCount = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    if (isLonely(picture, i, j)) {
                        lonelyCount++;
                    }
                }
            }
        }
        return lonelyCount;
    }

    private boolean isLonely(char[][] picture, int x, int y) {
        /// up
        if (x > 0) {
            for (int i = x - 1; i >= 0; i--) {
                if (picture[i][y] == 'B') {
                    return false;
                }
            }
        }
        /// down
        if (x < picture.length) {
            for (int i = x + 1; i < picture.length; i++) {
                if (picture[i][y] == 'B') {
                    return false;
                }
            }
        }
        /// left
        if (y > 0) {
            for (int j = y - 1; j >= 0; j--) {
                if (picture[x][j] == 'B') {
                    return false;
                }
            }
        }

        if (y < picture[0].length) {
            for (int j = y + 1; j < picture[0].length; j++) {
                if (picture[x][j] == 'B') {
                    return false;
                }
            }
        }
        return true;

    }

    public static void main(String[] args) {
        LeetCode531 leetCode531 = new LeetCode531();
        leetCode531.findLonelyPixel(new char[][]{
                {'W', 'W', 'B'},
                {'W', 'B', 'W'},
                {'B', 'W', 'W'}
        });
    }
}
