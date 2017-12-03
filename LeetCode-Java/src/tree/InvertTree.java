package Tree;

/**
 * Tree
 * Created by X-Liang
 * 2017-10-02-22:08
 *
 * @Description:
 */

/**
 * 翻转一颗二叉树
 */
public class InvertTree {
    /**
     * 翻转一个二叉树
     * @param tree  二叉树根节点
     * @return 翻转后的二叉树
     */
    public TreeNode invertTree(TreeNode tree) {
        // 1. 终止条件
        if (tree == null) { return tree; }

        // 2. 层级操作
        swap(tree.left, tree.right);

        // 3. 递归
        tree.left = invertTree(tree.left);
        tree.right =  invertTree(tree.right);

        return tree;
    }

    private void swap(TreeNode left, TreeNode right) {
        int temp = left.val;
        left.val = right.val;
        right.val = temp;
    }
}
