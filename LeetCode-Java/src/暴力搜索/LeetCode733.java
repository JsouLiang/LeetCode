package 暴力搜索;

public class LeetCode733 {
    /// (sr, sc)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private int[] dits = {1, 0, -1, 0, 1};
    private void dfs(int[][] image, int sr, int sc, int oColor, int nColor) {
        if (image[sr][sc] == nColor) {
            return;
        }
        image[sr][sc] = nColor;
        for (int i = 0; i < dits.length - 1; i++) {
            int newSr = sr + dits[i];
            int newSc = sc + dits[i + 1];
            if (newSr < 0 || newSc < 0 || newSr >= image.length || newSc >= image[0].length) {
                continue;
            }
            if (image[newSr][newSc] == oColor) {
                dfs(image, newSr, newSc, oColor, nColor);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode733 leetCode733 = new LeetCode733();
        leetCode733.floodFill(new int[][]{
                {1,1,1},{1,1,0},{1,0,1}
        }, 1, 1, 2);
    }
}
