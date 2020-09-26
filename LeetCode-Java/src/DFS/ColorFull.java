package DFS;

/**
 * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
 *
 * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的横坐标为 sr 纵坐标为 sc。需要填充的新颜色为 newColor 。
 *
 * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
 *
 * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
 *
 *
 * 示例：
 *
 * 输入：
 * image = [
 *  [1,1,1],
 *  [1,1,0],
 *  [1,0,1]
 * ]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[[2,2,2],[2,2,0],[2,0,1]]
 * 解释:
 * 初始坐标点位于图像的正中间，坐标 (sr,sc)=(1,1) 。
 * 初始坐标点周围区域上所有符合条件的像素点的颜色都被更改成 2 。
 * 注意，右下角的像素没有更改为 2 ，因为它不属于初始坐标点的周围区域。
 *
 */
public class ColorFull {
    int[][] image;
    int originalColor;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.image = image;
        this.originalColor = image[sr][sc];
        floodFill(sc, sr, newColor);
        return this.image;
    }

    private void floodFill(int x, int y, int newColor) {
        if (x < 0 || x >= image[0].length || y < 0 || y >= image.length) {
            return;
        }
        if (image[y][x] != originalColor || image[y][x] == newColor) {
            return;
        }
        image[y][x] = newColor;
        floodFill(x - 1, y, newColor);  // 左
        floodFill(x + 1, y, newColor);  // 右
        floodFill(x, y - 1, newColor);  // 上
        floodFill(x, y + 1, newColor);  /// 下
    }

    public static void main(String[] args) {
        /**
         * [
         * [0,0,0],
         * [1,0,0]
         * ]
         * 1
         * 0
         * 2
         */
        ColorFull colorFull = new ColorFull();
        colorFull.floodFill(new int[][]{
                {0, 0, 0},
                {1, 0, 0},
        }, 1, 0, 2);
    }
}
