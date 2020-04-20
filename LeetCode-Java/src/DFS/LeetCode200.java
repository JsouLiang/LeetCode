package DFS;

/**
200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。
 
*/
class LeetCode200 {
  public int numIslands(char[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          dfs(i, j, grid);
          count++;
        }
      }
    }  
    return count;
  }
  
  private void dfs(int x, int y, char[][] grid) {
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
      return ;
    }
    if (grid[x][y] == '0') {
      return ;
    }
    grid[x][y] = '0';
    /// 上
    dfs(x - 1, y, grid);
    /// 下
    dfs(x + 1, y, grid);
    /// 左
    dfs(x, y - 1, grid);
    /// 右
    dfs(x, y + 1, grid);
  }
  
  public static void main(String[] args) {
    LeetCode200 leetcode200 = new LeetCode200();
    leetcode200.numIslands(new char[][]{
      {'1','1','0','0','0'},
      {'1','1','0','0','0'},
      {'0','0','1','0','0'},
      {'0','0','0','1','1'}
    });
  }
}