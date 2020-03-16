package Tree.LeetCode;

/**
 * 333. 最大 BST 子树
 */
public class LeetCode333 {
    public int largestBSTSubtree(TreeNode root) {
        Res res = maxBSTSubtree(root);
        return res.bstNodeCount;
    }

    class Res {
        /// 是否是 BST
        boolean isBST;
        /// BST 节点个数
        int bstNodeCount;
        /// 当前 BST 最大值, 初始值为 MIN_VALUE
        int maxValue;
        /// 当前 BST 最小值, 初始值为 MAX_VALUE
        int minValue;

        public Res(boolean isBST, int bstNodeCount, int maxLeftValue, int minRightValue) {
            this.isBST = isBST;
            this.bstNodeCount = bstNodeCount;
            this.maxValue = maxLeftValue;
            this.minValue = minRightValue;
        }
    }

    private Res maxBSTSubtree(TreeNode root) {
        if (root == null) {
            /// 当为 null 时，返回 Res中 left 最大值为 MINVALUE，right 最小值为 MAXVALUE
            /// 这样父节点判断 root.val > left.maxValue, root.val < right.minValue 可以确保为 true
            return new Res(true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        if (root.left == null && root.right == null) {
            return new Res(true, 1, root.val, root.val);
        }
        Res leftRes = maxBSTSubtree(root.left);
        Res rightRes = maxBSTSubtree(root.right);
        /// isBSTNode
        if (leftRes.isBST && rightRes.isBST && root.val > leftRes.maxValue && root.val < rightRes.minValue) {
            /// 如果右子树提供的最大值为 MIN_Value 说明当前节点没有右子树，那么以当前节点为根的子树中最大值应该是根节点
            int maxValue = rightRes.maxValue == Integer.MIN_VALUE ? root.val : rightRes.maxValue;
            int minValue = leftRes.minValue == Integer.MAX_VALUE ? root.val : leftRes.minValue;
            return new Res(true, leftRes.bstNodeCount + rightRes.bstNodeCount + 1, maxValue, minValue);
        } else {
            int bstCount = Math.max(leftRes.bstNodeCount, rightRes.bstNodeCount);
            return new Res(false, bstCount, leftRes.maxValue, rightRes.minValue);
        }
    }

    public static void main(String[] args) {
        LeetCode333 leetCode333 = new LeetCode333();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{10, 5, 15, 1, 8, null, 17});
//        tree = TreeNode.createTreeWith(new Integer[]{2, 3, null, 1});
//        tree = TreeNode.createTreeWith(new Integer[]{2, null, 4, null, null, null, 5});
        leetCode333.largestBSTSubtree(tree);
    }
}
