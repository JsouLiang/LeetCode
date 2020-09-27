package Tree.LeetCode;

public class LeetCode235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = findAncestor(root, p, q);
        return res;
    }

    private TreeNode findAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node.val == p.val || node.val == q.val) {
            return node;
        }

        TreeNode leftRes = findAncestor(node.left, p, q);
        TreeNode rightRes = findAncestor(node.right, p, q);
        // 当前 node 节点从左子树和右子树中找到了 p，q，那么 node 为 LSA
        // 否则要么是还没有找到 LSA，要么 LSA 就是 leftRes 的结果或者 rightRes结果
        if (leftRes != null && rightRes != null) {
            return node;
        } else if (leftRes != null) {
            return leftRes;
        } else {
            return rightRes;
        }
    }

    public static void main(String[] args) {
        LeetCode235 leetCode235 = new LeetCode235();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{6,2,8,0,4,7,9,null,null,3,5});
        leetCode235.lowestCommonAncestor(tree, new TreeNode(9), new TreeNode(3));
    }
}
