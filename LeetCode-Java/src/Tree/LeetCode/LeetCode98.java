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

    private class ResultTypeOfNode {
        boolean isBST;
        TreeNode minNode;
        TreeNode maxNode;

        public ResultTypeOfNode(boolean isBST) {
            this.isBST = isBST;
            minNode = null;
            maxNode = null;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return checkIsBST(root).isBST;
    }

    private ResultTypeOfNode checkIsBST(TreeNode root) {
        if (root == null) {
            return new ResultTypeOfNode(true);
        }
        ResultTypeOfNode leftSubtreeCheckResult = checkIsBST(root.left);
        ResultTypeOfNode rightSubTreeCheckResult = checkIsBST(root.right);
        return margin(root, leftSubtreeCheckResult, rightSubTreeCheckResult);
    }

    private ResultTypeOfNode margin(TreeNode root, ResultTypeOfNode leftResult, ResultTypeOfNode rightResult) {
        if (!leftResult.isBST || !rightResult.isBST) {
            return new ResultTypeOfNode(false);
        }
        if (leftResult.maxNode != null && leftResult.maxNode.val >= root.val ||
            rightResult.minNode != null && root.val >= rightResult.minNode.val) {
            return new ResultTypeOfNode(false);
        }
        ResultTypeOfNode result = new ResultTypeOfNode(true);
        result.minNode = leftResult.minNode != null ? leftResult.minNode : root;
        result.maxNode = rightResult.maxNode != null ? rightResult.maxNode : root;
        return result;
    }


    private class ResultTypeOfValue {
        boolean isBST;
        int maxValue;
        int minValue;

        public ResultTypeOfValue(boolean isBST, int maxValue, int minValue) {
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }
    public boolean isValidBSTByCheckValue(TreeNode root) {
        return checkIsBSTUseValue(root).isBST;
    }

    private ResultTypeOfValue checkIsBSTUseValue(TreeNode root) {
        if (root == null) {
            return new ResultTypeOfValue(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ResultTypeOfValue leftSubtreeResult = checkIsBSTUseValue(root.left);
        ResultTypeOfValue rightSubtreeResult = checkIsBSTUseValue(root.right);
        return marginOfResultValue(root, leftSubtreeResult, rightSubtreeResult);
    }

    private ResultTypeOfValue marginOfResultValue(TreeNode root, ResultTypeOfValue leftResult, ResultTypeOfValue rightResult) {
        if (!leftResult.isBST || !rightResult.isBST) {
            return new ResultTypeOfValue(false, 0, 0);
        }
        if (root.left != null && leftResult.maxValue >= root.val ||
                root.right != null &&root.val >= rightResult.minValue) {
            // is BST
            return new ResultTypeOfValue(false, 0,0 );
        }
        ResultTypeOfValue resultTypeOfValue = new ResultTypeOfValue(true,
                Math.max(root.val, rightResult.maxValue),       // maxValue
                Math.min(root.val, leftResult.minValue)         // minValue
        );
        return resultTypeOfValue;
    }

    public static void main(String[] args) {
        LeetCode98 leetCode98 = new LeetCode98();
        TreeNode root = TreeNode.createTreeWith(new Integer[] {5,1,4,null,null,3,6});
        boolean result =  leetCode98.isValidBSTByCheckValue(root);

        root = TreeNode.createTreeWith(new Integer[] {2, 1, 3});
        result =  leetCode98.isValidBSTByCheckValue(root);

        root = TreeNode.createTreeWith(new Integer[] {1, 1});
        result =  leetCode98.isValidBSTByCheckValue(root);
        System.out.println(result);
    }
}
