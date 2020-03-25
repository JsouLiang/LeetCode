package Basic;

import Tree.LeetCode.LeetCode450;

public class LeetCode892 {
    public int surfaceArea(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                /// 上下两个底，level 层 4 个面
                int level = grid[i][j];
                if (level == 0) {
                    continue;
                }
                res += (2 + 4 * level);
                /// 如果前一行有立方体
                if (i > 0 && grid[i - 1][j] > 0) {
                    /// 重叠的面积，2 倍
                    int preLineLevel = grid[i - 1][j];
                    res -= Math.min(level, preLineLevel) * 2;
                }
                if (j > 0 && grid[i][j - 1] > 0) {
                    /// 重叠的面积，2 倍
                    int preLineLevel = grid[i][j - 1];
                    res -= Math.min(level, preLineLevel) * 2;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode892 leetCode892 = new LeetCode892();
        leetCode892.surfaceArea(new int[][]{{2}});  // 10
        leetCode892.surfaceArea(new int[][]{{1, 2}, {3, 4}});  // 34
        leetCode892.surfaceArea(new int[][]{{1, 0}, {0, 2}});  // 16
        leetCode892.surfaceArea(new int[][]{
                {1,1,1},{1,0,1},{1,1,1}
        });  // 32
        leetCode892.surfaceArea(new int[][]{
                {2, 2, 2},{2, 1, 2},{2, 2, 2}
        });  // 64
    }
}
