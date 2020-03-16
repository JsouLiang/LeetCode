package DFS;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1的非空二维数组 grid ,
 * 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 */
public class LeetCode695 {

    private int[] directions = new int[]{-1, 0, 1, 0, -1};

    public int maxAreaOfIsland(int[][] grid) {
        int islandSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    islandSize = Math.max(islandSize, currentIslandSize(grid, i, j));
                }
            }
        }
        return islandSize;
    }

    private int currentIslandSize(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int size = 1;
        for (int index = 0; index < directions.length - 1; index++) {
            int nextI = i + directions[index];
            int nextJ = j + directions[index + 1];
            if (nextI >= 0 &&
                    nextI < grid.length &&
                    nextJ >= 0 &&
                    nextJ < grid[0].length &&
                    grid[nextI][nextJ] == 1) {
                size += currentIslandSize(grid, nextI, nextJ);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        LeetCode695 leetCode695 = new LeetCode695();
        leetCode695.maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}

        });
        leetCode695.maxAreaOfIsland(new int[][]{
                {0,0,0,0,0,0,0,0},
        });
        leetCode695.maxAreaOfIsland(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1},
        });
    }
}
