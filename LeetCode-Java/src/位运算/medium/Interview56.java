package 位运算.medium;

/**
 * 面试题56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
public class Interview56 {
    /**
     * 思路：
     * 1. 将数分2组，每组只有一个数仅出现一次
     * 2. 分组方案
     *  2.1.找 mask 值，因为我们只需要将两个只出现一次的值分开，所以我们可以将所有值进行异或，最后结果就是这两个值异或的结果
     *  2.2.找到最低位的 1 的值作为 mask
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int xorAllNumRes = 0;
        for (int num : nums) {
            xorAllNumRes ^= num;
        }
        int mask = 1;
        while ((xorAllNumRes & 1) == 0) {
            mask <<= 1;
            xorAllNumRes >>= 1;
        }
        int a = 0; int b = 0;
        for (int num : nums) {
            /// num & mask 结果有两种情况 0 或者非零
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        Interview56 interview56 = new Interview56();
        interview56.singleNumbers(new int[]{1,2,10,4,1,4,3,3});
    }
}
