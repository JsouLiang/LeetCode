package DP;

/**
 * DP
 * Created by X-Liang
 * 2017-10-07-21:08
 *
 * @Description: 背包问题
 */
public class Backpacker {
    /**
     * 01 背包
     * 小偷有一个容量为W的背包，有n件物品，
     * 第i个物品价值vi，且重wi
     * 目标:找到xi使得对于所有的xi={0,1}
     * sum(wi*xi)<=W,并且sum(xi*vi)最大
     *
     */
    class Backpacker01 {
        int   count;      // 物品数量
        int[] w;          // 每件物品的重量
        int[] v;          // 每件物品的价值
        int totalWeight;  // 背包总重量

        int[][] status;
        /**
         * 背包算法-记忆化搜索
         * @param index 当前搜索第几个
         * @param currentWeight 当前背包重量
         * @return
         * @deprecated 注意状态冗余，我们在第 index 次选择时，背包重量到 currentWeight时会有多种选择
         * 如第二次选择达到 4 即 index = 2，currentWight = 4
         * 选法如下：
         * 1 3
         * 3 1
         * 2 2
         * 我们只关心 index = 2，currentWight = 4 这种状态，在这种状态下产生的最大价值是固定的即最大状态必然
         * 从上面三种中其一，如果我们每次到达这种状态(2, 4), 就搜索一次所有选择，这样就会产生冗余
         * 所以我们可以记录(2, 4) 状态下的最大值，下次到达这种状态可直接返回
         */
        public int search(int index, int currentWeight) {
            if (currentWeight > totalWeight) return 0;
            if (index >= count) return 0;
            if (status[index][currentWeight] != -1) {
                return status[index][currentWeight];
            }
            // 记录状态最大值
            int value = Math.max(
                    // 选择第 index 个物品，重量，价值都会发生改变
                    search(index + 1, currentWeight + w[index]) + v[index],
                    // 不选择第 index 个物品
                    search(index + 1, currentWeight)
            );
            status[index][currentWeight] = value;

            return value;
        }

        public int searchII() {
            int[][] result = new int[count][totalWeight];
            result[0][0] = 0;
            for (int i = 1; i < count; i++) {
                result[i][0] = 0;       // currentWeight = 0 时，value = 0
                for (int j = 1; j < totalWeight; j++) {
                    // f[currentIndex][currentWeight] =
                    // max(f[currentIndex + 1][currentWeight + w[currentIndex]] + value[currentIndex], f[currentIndex + 1][currentWeight]);
                    // result[i][j] = Math.max(result[i + 1][j + w[i]] + v[i], result[i + 1][j]);
                    // 第[i][j] 状态是由上一个：选择上一个物品时，空间变为j - w[i]价值加上 v[i]
                    // 不选择：[i - 1][j]
                    // 如果当前状态为(i, j) ，如果我要选第 i 个物品，那么上一次的剩余空间就要为 j - w[i]
                    result[i][j] = Math.max(result[i - 1][j - w[i]] + v[i], result[i - 1][j]);
                }
            }
            return result[count - 1][totalWeight - 1];
        }
    }
}
