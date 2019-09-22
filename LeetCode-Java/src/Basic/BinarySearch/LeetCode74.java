package Basic.BinarySearch;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 *     每行中的整数从左到右按升序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class LeetCode74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 这个题要注意边界处理呀
        // 行数 ,  列数
        int rowCount = matrix.length, columnCount = matrix[0].length;
        if (columnCount == 0) {
            return false;
        }
        int right = rowCount * columnCount - 1;
        int left = 0;
        while (left + 1 < right) {
            // 将位置映射成二维数组下标
            int middle = left + (right - left) / 2;
            // 行
            int row = middle / columnCount;
            // 列
            int column = middle % columnCount;
            if (matrix[row][column] > target) {
                right = middle;
            } else if (matrix[row][column] < target) {
                left = middle;
            } else {
                return true;
            }
        }
        if (left >= 0) {
            // 行
            int row = left / columnCount;
            // 列
            int column = left % columnCount;
            if (matrix[row][column] == target) {
                return true;
            }
        }
        if (right >= 0) {
            // 行
            int row = right / columnCount;
            // 列
            int column = right % columnCount;
            if (matrix[row][column] == target) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode74 leetCode74 = new LeetCode74();
        boolean result = leetCode74.searchMatrix(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3);      // true
        result = leetCode74.searchMatrix(new int[][]{
                {1}
        }, 1);      // true
        result = leetCode74.searchMatrix(new int[][]{
                {1, 3}
        }, 3);      // true
        result = leetCode74.searchMatrix(new int[][]{
                {}
        }, 1);      //false
        result = leetCode74.searchMatrix(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 13);
        ;
        result = leetCode74.searchMatrix(new int[][] { {-9,-8},
                {-5,-3},
                {-1, 1},
                {4, 4}}, -15);

        System.out.println(result);
    }
}
