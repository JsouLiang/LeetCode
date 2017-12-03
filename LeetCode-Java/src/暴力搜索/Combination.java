package 暴力搜索;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 暴力搜索
 * Created by X-Liang
 * 2017-12-03-15:26
 *
 * @Description: 组合问题
 */
public class Combination {

    /**
     * 从 Data list 中选取 n 个数进行组合排列
     * @param data 待选数组
     * @param n 选择几个数进行排列
     */
    public void combinations(List<Integer>selectedItems, List<Integer> data, int n) {
        if (n == 0) { // 从集合中选择0个元素
            // 输出所有已选的元素
            for (Integer i: selectedItems) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.print("\n");
            return;
        }

        if (data.isEmpty()) {
            return ;
        }

        // 选择第一个元素
        selectedItems.add(data.get(0));
        combinations(selectedItems, data.subList(1, data.size()), n - 1);
        // 不选择第一个元素
        // selectedItems.remove(data.get(0));  // data.get(0) 就是 selectedItems 中最后一个元素，
        // 所以
        selectedItems.remove(selectedItems.size() - 1);
        combinations(selectedItems, data.subList(1, data.size()), n);

    }

    public static void main(String[] args) {
        Combination comb = new Combination();
        System.out.print("-----\n");
        comb.combinations(new ArrayList<>(), Arrays.asList(), 0);
        System.out.print("-----\n");
        comb.combinations(new ArrayList<>(), Arrays.asList(), 2);
        System.out.print("-----\n");
        comb.combinations(new ArrayList<>(), Arrays.asList(1,2, 3,4), 1);
        System.out.print("-----\n");
        comb.combinations(new ArrayList<>(), Arrays.asList(1,2,3,4), 2);
    }
}
