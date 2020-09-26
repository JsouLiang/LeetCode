package TreeArray;

import Link.leetcode.easy.Interview0201;
import com.sun.source.tree.Tree;

import java.util.*;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 */
public class LeetCode315 {

    class TreeArray {

        int[] nums;
        int[] tree;
        int len;
        TreeArray(int[] nums) {
            this.nums = nums;
            this.len = nums.length;
            this.tree = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }
        }

        TreeArray(int len) {
            this.len = len;
            tree = new int[len + 1];

        }

        /**
         * 单点更新：将 index 下标值加上 delta
         * @param index
         * @param delta
         */
        public void update(int index, int delta) {
            while (index < len) {
                tree[index] += delta;
                index += lowBit(index);
            }
        }

        /**
         * 查询前缀和[1 ~ i]
         * @param i
         * @return
         */
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowBit(i);
            }
            return sum;
        }

        /// lowBit 函数找出 value 二进制位中左边第一个1 出现的位置的值
        /// 5 => 101 第一个出现 1 的二进制值为 1 十进制为 1
        /// 7 => 110 第一个出现 1 的二进制值为 10 十进制为 2
        /// 如何找到这个 1
        /// 110 取反得到 001，001 再加 1 得到 010
        /// 110 & 010 得到 010
        private int lowBit(int value) {
            // value & ^value + 1
            // => value & -value
            return value & -value;
        }


    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        /// 使用 set 过滤重复元素
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        /// 离散化
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (Integer num: set) {
            map.put(num, rank++);
        }
        TreeArray treeArray = new TreeArray(set.size() + 1);
        for (int i = nums.length - 1; i >= 0; i--) {
            rank = map.get(i);
            treeArray.update(rank, 1);
            res.add(treeArray.query(rank - 1));

        }
        return res;
    }

    public static void main(String [] args) {
        LeetCode315 leetCode315 = new LeetCode315();
        leetCode315.countSmaller(new int[]{5, 2, 6, 1});
    }
}
