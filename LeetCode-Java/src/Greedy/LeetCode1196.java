package Greedy;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 1196. 最多可以买到的苹果数量
 * 楼下水果店正在促销，你打算买些苹果，arr[i] 表示第 i 个苹果的单位重量。
 *
 * 你有一个购物袋，最多可以装 5000 单位重量的东西，算一算，最多可以往购物袋里装入多少苹果。
 *
 */
public class LeetCode1196 {
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int currentWeight = 0;
        for (int i = 0; i < arr.length; i++) {
            currentWeight += arr[i];
            if (currentWeight < 5000) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode1196 leetCode1196 = new LeetCode1196();
        leetCode1196.maxNumberOfApples(new int[]{100,200,150,1000});
        leetCode1196.maxNumberOfApples(new int[]{900,950,800,1000,700,800});
    }
}
