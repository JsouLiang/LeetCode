package Basic.sort;

import java.awt.*;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 */
public class LeetCode75 {
    enum Colors {
        Red("red", 0), White("white", 1), Blue("blue", 2);

        private String name;
        private int index;

        Colors(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    public void sortColors(int[] nums) {
        int redIndex = 0, blueIndex = nums.length - 1;
        int finishedIndex = 0;
        while (finishedIndex <= blueIndex) {
            if (nums[finishedIndex] == Colors.Blue.index) {
                // move blue to end
                nums[finishedIndex] = nums[blueIndex];
                nums[blueIndex--] = Colors.Blue.index;
            } else if (nums[finishedIndex] == Colors.Red.index) {
                // red at right index so finishedIndex++
                nums[finishedIndex++] = nums[redIndex];
                nums[redIndex++] = Colors.Red.index;
            } else {
                // 此时 finished 指向1，因为 finished 前面是指向0或者1且已经是排好的了
                finishedIndex++;
            }

        }
    }

    public static void main(String[] args) {
        LeetCode75 leetCode75 = new LeetCode75();
        int[] colors = new int[]{2, 0, 2, 1, 1, 0};
        leetCode75.sortColors(colors);
        System.out.println(colors);
    }
}
