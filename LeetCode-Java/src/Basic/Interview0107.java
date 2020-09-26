package Basic;

/**
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class Interview0107 {
    public void rotate(int[][] matrix) {
        // 先以对角线进行交换
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                swap(i, j, j, i, matrix);
            }
        }
        // 每行按中心旋转
        int middle = matrix.length >> 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < middle; j++) {
                swap(i, j, i, matrix.length - 1 - j, matrix);
            }
        }
        System.out.println(matrix);
    }
    void swap(int fromX, int fromY, int targetX, int targetY, int[][] matrix) {
        int temp = matrix[fromX][fromY];
        matrix[fromX][fromY] = matrix[targetX][targetY];
        matrix[targetX][targetY] = temp;
    }

    public static void main(String [] args) {
        Interview0107 interview0107 = new Interview0107();
        interview0107.rotate(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
    }
}
