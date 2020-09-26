package Basic;

public class Interview29 {
    public int[] spiralOrder(int[][] matrix) {
        /// 行数, 列数
        int rows = matrix.length; int columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int totalCount = rows * columns;
        int[] res = new int[totalCount];
        int row = 0, column = -1;
        /// 0 初始状态
        /// 1 向左
        /// 2 向下
        /// 3 向右
        /// 4 向上
        int currentState = 0;
        for (int index = 0; index < totalCount; index++) {
            if (currentState == 0) {
                currentState = 1;
                column++;
            } else if (currentState == 1) {
                column++;
                if (column < columns && !visited[row][column]) {
                    currentState = 1;
                } else {
                    /// column 此时已经是列边界，故需要回退一格
                    column--;
                    row++;
                    currentState = 2;
                }
            } else if (currentState == 2) {
                row++;
                if (row < rows && !visited[row][column]) {
                    currentState = 2;
                } else {
                    /// row 当前已经到达边界，需要回退
                    row--;
                    currentState = 3;
                    /// 由向下转成向右，column 应该--
                    column--;
                }
            }
            else if (currentState == 3) {
                column--;
                if (column >= 0 && !visited[row][column]) {
                    currentState = 3;
                } else {
                    /// column 当前已经到达边界，需要回退
                    column++;
                    currentState = 4;
                    /// 有向左转成向上，row 应该--
                    row--;
                }
            } else {
                row--;
                if (row >= 0 && !visited[row][column]) {
                    currentState = 4;
                } else {
                    /// row 当前已经到达边界，需要回退
                    row++;
                    currentState = 1;
                    /// 有向上转为向右，column 应该 ++
                    column++;
                }
            }
            res[index] = matrix[row][column];
            visited[row][column] = true;
        }
        return res;
    }

    public static void main(String[] args) {
        Interview29 interview29 = new Interview29();
        interview29.spiralOrder(new int[][] {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        });
    }
}
