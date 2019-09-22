package BFS;

import Tree.LeetCode.TreeNode;
import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // current level
            int nodeSize = queue.size();
            List<Integer> currentLevelNodes = new ArrayList<>();
            for (int i = 0; i < nodeSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelNodes.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevelNodes);
        }
        return result;
    }
    public static void main(String[] args) {
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{3,9,20,null,null,15,7});

        LeetCode102 leetCode102 = new LeetCode102();
        List<List<Integer>> result =  leetCode102.levelOrder(tree);
        System.out.println(result);
    }
}
