package Tree.LeetCode;

import javafx.css.Match;
import jdk.nashorn.api.tree.Tree;

import java.util.regex.Matcher;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 */
public class LeetCode543 {
    private int maxCount = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        deepOfNode(root);
        return maxCount - 1;
    }

    private int deepOfNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = deepOfNode(root.left);
        int rightDeep = deepOfNode(root.right);
        /// 当前节点的直径
        int currentCount = leftDeep + rightDeep + 1;
        maxCount = Math.max(maxCount, currentCount);
        /// 返回节点深度
        return Math.max(leftDeep, rightDeep) + 1;
    }

    public static void main(String[] args) {
        LeetCode543 leetCode543 = new LeetCode543();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1, 2, 3, 4, 5});
        leetCode543.diameterOfBinaryTree(tree);
    }
}
