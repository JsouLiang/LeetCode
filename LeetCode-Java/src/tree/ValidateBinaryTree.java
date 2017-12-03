package Tree;

/**
 * Tree
 * Created by X-Liang
 * 2017-10-02-22:25
 *
 * @Description: 给定一个二叉树，判断是否是二叉搜索树
 */
public class ValidateBinaryTree {
    private long pre = Long.MIN_VALUE;

    /**
     * Solution1:
     * 二叉搜索树的中序遍历递增
     * 所以对树做一个中序遍历
     *
     * @param treeNode 树根节点
     * @return 是否是二叉搜索树
     */
    public boolean validateBinaryTree(TreeNode treeNode) {
        if (treeNode == null) {
            return true;
        }

        /**
         * 判断左子树是否是二叉搜索树
         * 进行中间节点的操作
         * 判断右子树是否是二叉搜索树
         */
        if (!validateBinaryTree(treeNode.left)) return false;

        // 如果某个节点小于前一个节点的值，那么该树肯定不是二叉搜索树
        if (treeNode.val <= pre) return false;
        pre = treeNode.val; // 记录前一个值

        if (!validateBinaryTree(treeNode.right)) return false;

        return true;
    }

    /**
     * Solution:
     * 左子树的最大值 < root < 右子树最小值
     *
     * @param treeNode 树根节点
     * @return 是否是二叉搜索树
     */
    public boolean isValidBST(TreeNode treeNode) {
        if (treeNode == null || (treeNode.left == null && treeNode.right == null)) {
            return true;
        } else {
            if (treeNode.right == null) {
                return validLeftTree(treeNode);
            } else if (treeNode.left == null) {
                return validRightTree(treeNode);
            } else {
                return validRightTree(treeNode) && validLeftTree(treeNode);
            }
        }
    }

    private boolean validLeftTree(TreeNode treeNode) {
        return (treeNode.val > treeNode.left.val &&
                treeNode.val > getLeftSubTreeMaximumValue(treeNode.left) &&
                isValidBST(treeNode.left));
    }

    private boolean validRightTree(TreeNode treeNode) {
        return (treeNode.val < treeNode.right.val &&
                treeNode.val < getRightSubTreeMinimumValue(treeNode.right) &&
                isValidBST(treeNode.right));
    }

    /**
     * 获得右子树中最小的值
     * @param rightSubTree 右子树
     * @return 最小值
     */
    private Integer getRightSubTreeMinimumValue(TreeNode rightSubTree) {
        // 如果一颗子树左节点为空，那么该根节点就是右子树的最小值
        if (rightSubTree.left == null) {
            return rightSubTree.val;
        }
        // 右子树最小值为右子树最左边的节点的值
        return getRightSubTreeMinimumValue(rightSubTree.left);
    }

    /**
     * 获得左子树中最小值
     * @param leftSubTree 左子树
     * @return  最小值
     */
    private Integer getLeftSubTreeMaximumValue(TreeNode leftSubTree) {
        // 如果一颗子树右节点为空，那么该根节点就是左子树的最大值
        if (leftSubTree.right == null) {
            return leftSubTree.val;
        }
        // 左子树最大值为左子树最右边的节点的值
        return getLeftSubTreeMaximumValue(leftSubTree.right);
    }
}
