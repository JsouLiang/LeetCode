package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 *     节点的左子树只包含小于当前节点的数。
 *     节点的右子树只包含大于当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 *
 */
public class LeetCode98 {

    private class ResultType {
        boolean isBST;
        TreeNode minNode;
        TreeNode maxNode;

        public ResultType(boolean isBST) {
            this.isBST = isBST;
            minNode = null;
            maxNode = null;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return checkIsBST(root).isBST;
    }

    private ResultType checkIsBST(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }
        ResultType leftSubtreeCheckResult = checkIsBST(root.left);
        ResultType rightSubTreeCheckResult = checkIsBST(root.right);
        return margin(root, leftSubtreeCheckResult, rightSubTreeCheckResult);
    }

    private ResultType margin(TreeNode root, ResultType leftResult, ResultType rightResult) {
        if (!leftResult.isBST || !rightResult.isBST) {
            return new ResultType(false);
        }
        if (leftResult.maxNode != null && leftResult.maxNode.val >= root.val ||
            rightResult.minNode != null && root.val >= rightResult.minNode.val) {
            return new ResultType(false);
        }
        ResultType result = new ResultType(true);
        result.minNode = leftResult.minNode != null ? leftResult.minNode : root;
        result.maxNode = rightResult.maxNode != null ? rightResult.maxNode : root;
        return result;
    }

    public static void main(String[] args) {
        LeetCode98 leetCode98 = new LeetCode98();
        TreeNode root = TreeNode.createTreeWith(new Integer[] {5,1,4,null,null,3,6});
        boolean result =  leetCode98.isValidBST(root);

        root = TreeNode.createTreeWith(new Integer[] {2, 1, 3});
        result =  leetCode98.isValidBST(root);
        System.out.println(result);
    }
}
