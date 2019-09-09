package Tree.SegmentTree;

public class SegmentTree {
    private int[] nums;
    private Node[] nodes;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        if (nodes == null) {
            /// 对于有 n 个节点的完全二叉树来说，非叶子节点有 n + 1 个
            /// 所以 n 个节点的完全二叉树一共有 2n + 1 个节点
            nodes = new Node[nums.length << 1 + 2];
        }
        build(0);
    }

    private void build(int index) {
        Node node = nodes[index];
        if (node == null) {
            nodes[index] = new Node(0, nums.length - 1);
            node = nodes[index];
        }
        /// node.start == node.end 表示这是个叶子节点
        if (node.start == node.end) {
            node.data = node.start;
        } else {
            /// 构建左右子树
            int mid = (node.start + node.end) >> 1;
            /// 如果使用数组存储一个完全二叉树时：
            /// 如果根节点坐标是0，那么
            /// 左孩子：index * 2 + 1   ===  (index << 1) + 1
            /// 右孩子：index * 2 + 2   ===  (index << 1) + 2
            int leftChildIndex = (index << 1) + 1;
            int rightChildIndex = (index << 1) + 2;
            nodes[leftChildIndex] = new Node(node.start, mid);
            nodes[rightChildIndex] = new Node(mid + 1, node.end);
            /// 构建左孩子
            build(leftChildIndex);
            /// 构建右孩子
            build(rightChildIndex);
            /// 当前节点的值
            node.data = Math.min(nodes[leftChildIndex].data, nodes[rightChildIndex].data);
        }
    }
}
