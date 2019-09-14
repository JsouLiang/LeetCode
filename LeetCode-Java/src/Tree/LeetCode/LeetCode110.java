package Tree.LeetCode;

import com.sun.source.tree.Tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 *     一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 *
 * 返回 false 。
 */
public class LeetCode110 {
    private class ResultType {
        boolean isBalanced;
        int height;

        public ResultType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return isBalanceTree(root).isBalanced;
    }

    private ResultType isBalanceTree(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }
        ResultType leftResult = isBalanceTree(root.left);
        ResultType rightResult = isBalanceTree(root.right);

        if (leftResult.isBalanced && rightResult.isBalanced && Math.abs(leftResult.height - rightResult.height) <= 1) {
            return new ResultType(true, Math.max(leftResult.height, rightResult.height) + 1);
        }
        return new ResultType(false, Math.max(leftResult.height, rightResult.height) + 1);
    }

    public static void main(String[] args) {
        LeetCode110 leetCode110 = new LeetCode110();
        TreeNode root = TreeNode.createTreeWith(new Integer[]{3,9,20,null,null,15,7});
        boolean result = leetCode110.isBalanced(root);

        root = TreeNode.createTreeWith(new Integer[]{1,2,2,3,3,null,null,4,4});
        result = leetCode110.isBalanced(root);
        root = TreeNode.createTreeWith(new Integer[]{1,null,2,null,3});
        result = leetCode110.isBalanced(root);

        System.out.println(result);
    }
}
