package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 572. 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 */
public class LeetCode572 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> subTrees = new ArrayList<>();
        // 1. 从 s 中找到与 t 根节点相同的子树
        findSubTree(s, t, subTrees);
        if (subTrees.isEmpty()) {
            return false;
        }
        // 2. 比较两棵树
        for (TreeNode subTree: subTrees) {
            boolean res = compareTree(subTree, t);
            if (res == true) {
                return true;
            }
        }
        return false;
    }

    private void findSubTree(TreeNode root, TreeNode t, List<TreeNode> res) {
        if (root == null) {
            return ;
        }
        if (root.val == t.val) {
            res.add(root);
        }
        findSubTree(root.left, t, res);
        findSubTree(root.right, t, res);
    }

    private boolean compareTree(TreeNode root1, TreeNode root2) {
        /// root
        if (root1 == null && root2 == null) {
            return true;
        }
        /// root1 != null, root2 != null, but value not equal
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        boolean leftCompare = compareTree(root1.left, root2.left);
        boolean rightCompare = compareTree(root1.right, root2.right);
        /// 左子树都相等，右子树都相等
        return leftCompare == true && rightCompare == true;
    }

    public static void main(String[] args) {
        LeetCode572 leetCode572 = new LeetCode572();
        TreeNode s = TreeNode.createTreeWith(new Integer[]{3, 4, 5, 1, 2});
        TreeNode t = TreeNode.createTreeWith(new Integer[]{4, 1, 2});
        leetCode572.isSubtree(s, t);
        s = TreeNode.createTreeWith(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0});
        leetCode572.isSubtree(s, t);

        s = TreeNode.createTreeWith(new Integer[]{1, 1});
        t = TreeNode.createTreeWith(new Integer[]{1, });
        leetCode572.isSubtree(s, t);

    }

}
