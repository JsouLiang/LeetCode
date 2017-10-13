package tree;

import apple.laf.JRSUIUtils;

/**
 * tree
 * Created by X-Liang
 * 2017-10-03-10:23
 *
 * @Description:
 * 最近公共祖先
 * BST 的最近公共祖先问题
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 *    _______6______
 *   /              \
 *  ___2__          ___8__
 * /      \        /      \
 * 0      _4       7       9
 * /  \
 * 3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.


 */
public class LowestCommonAncestorOfBST {

    public TreeNode solution2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) { return null; }

        if (p.val < root.val && q.val < root.val) return solution2(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return solution2(root.right, p, q);
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode left, righ;
        if (p.val > q.val) {
            left = q; righ = p;
        } else {
            left = p; righ = q;
        }
        return findParent(root, left, righ);
    }

    private TreeNode findParent(TreeNode root, TreeNode left, TreeNode right) {
        if (root.val < left.val) {
            return findParent(root.right, left, right);
        } else if (root.val > right.val) {
            return findParent(root.left, left, right);
        } else {
            return root;
        }
    }
}
