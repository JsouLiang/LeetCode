package BFS;

import Tree.LeetCode.TreeNode;
import jdk.nashorn.api.tree.Tree;

import java.util.*;

/**
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class LeetCode107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> resStack = new Stack<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            final int levelSize = queue.size();
            List<Integer> currentLevelValue = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelValue.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            resStack.push(currentLevelValue);
        }
        while (!resStack.isEmpty()) {
            res.add(resStack.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{3,9,20,null,null,15,7});
        LeetCode107 leetCode107 = new LeetCode107();
        List<List<Integer>> res = leetCode107.levelOrderBottom(tree);
        System.out.println(res);
    }
}
