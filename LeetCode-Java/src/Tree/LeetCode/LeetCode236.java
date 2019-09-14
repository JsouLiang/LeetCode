package Tree.LeetCode;

import Queue.单调队列.LeetCode239;
import jdk.nashorn.api.tree.Tree;

/**
 * 236. 二叉树的最近公共祖先
 * LCA系列问题
 */
public class LeetCode236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /// 从 root.left 找到了一个 p 或 q
        /// 从 root.right 中找到了另一个 p 或 q
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCode236 leetCode236 = new LeetCode236();
        TreeNode root = TreeNode.createTreeWith(new Integer[]{6,2,8,0,4,7,9,null,null,3,5});
        TreeNode node =  leetCode236.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8));
        System.out.println(node);
    }
}
