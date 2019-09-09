package Tree.SegmentTree.LeetCode;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 *
 * 说明:
 *
 *     数组仅可以在 update 函数下进行修改。
 *     你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 */
public class LeetCode307 {
    private class Node {
        private int start;
        private int end;
        private int sum;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }

    private Node[] nodes;
    private int[] nums;
    public LeetCode307(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        this.nums = nums;
        final int size = nums.length;
        nodes = new Node[size << 1 + 2];
        buildSegmentTree(0);
        System.out.println(nodes);
    }

    private void buildSegmentTree(int index) {
        Node node = nodes[index];
        if (node == null) {
            node = new Node(0, nums.length - 1);
            nodes[index] = node;
        }
        if (node.start == node.end) {
            node.sum = nums[node.start];
        } else {
            int middle = (node.start + node.end) / 2;
            int leftChildIndex = (index << 1) + 1;
            int rightChildIndex = (index << 1) + 2;
            nodes[leftChildIndex] = new Node(node.start, middle);
            nodes[rightChildIndex] = new Node(middle + 1, node.end);
            buildSegmentTree(leftChildIndex);
            buildSegmentTree(rightChildIndex);
            node.sum = nodes[leftChildIndex].sum + nodes[rightChildIndex].sum;
        }
    }

    public void update(int targetIndex, int val) {
        update(0, targetIndex, val);
    }

    private void update(int index, int targetIndex, int val) {
        final Node node = nodes[index];
        if (node.start == node.end) {
            nums[node.start] = val;
            node.sum = val;
            return;
        }
        final int middle = (node.start + node.end) / 2;
        final int leftChildIndex = (index << 1) + 1;
        final int rightChildIndex = (index << 1) + 2;
        if (targetIndex <= middle) {
            /// update left range
            update(leftChildIndex, targetIndex, val);
        } else {
            /// update right range
            update(rightChildIndex, targetIndex, val);
        }
        node.sum = nodes[leftChildIndex].sum + nodes[rightChildIndex].sum;
    }

    public int sumRange(int i, int j) {
        return sumRange(0, i, j);
    }

    private int sumRange(int index, int i, int j) {
        final Node node = nodes[index];
        /// 求和区间包含节点区间
        if (node.start >= i && node.end <= j) {
            return node.sum;
        }
        final int middle = (node.start + node.end) / 2;
        int sum = 0;
        if (i <= middle) {
            sum += sumRange((index << 1) + 1, i, j);
        }
        if (j > middle) {
            sum += sumRange((index << 1) + 2, i, j);
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode307 leetCode307 = new LeetCode307(new int[]{1, 3, 5});
        int sum = leetCode307.sumRange(0, 2);
        leetCode307.update(1, 2);
//        sum = leetCode307.sumRange(1, 2);
//        leetCode307.update(2, 2);
        sum = leetCode307.sumRange(0, 2);
    }
}
