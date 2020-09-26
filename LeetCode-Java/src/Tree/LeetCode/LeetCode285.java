package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

/**
 * 285. 二叉搜索树中的顺序后继
 */
public class LeetCode285 {
    TreeNode prev;
    TreeNode res;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }

    private void inorder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        inorder(root.left, p);
        if (res == null && prev != null && prev.val == p.val) {
            res = root;
            return;
        }
        prev = root;
        inorder(root.right, p);
    }

    public static void main(String[] args) {
        LeetCode285 leetCode285 = new LeetCode285();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{5,3,6,2,4,null,null,1});
        leetCode285.inorderSuccessor(tree, new TreeNode(1));
    }
}
