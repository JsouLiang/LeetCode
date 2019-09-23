package BFS;

import Tree.LeetCode.TreeNode;
import jdk.nashorn.api.tree.Tree;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * @implNote 二叉树层次遍历
 */
public class LeetCode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            final int currentLevelSize = queue.size();
            List<Integer> currentLeveRes = new LinkedList<>();
            List<Integer> currentChildren = new ArrayList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentChildren.add(currentNode.val);

                currentLeveRes.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            if (level % 2 == 1) {
                // 反转数组
                Collections.reverse(currentChildren);
                res.add(currentChildren);
            } else {
                res.add(currentLeveRes);
            }
            level++;
        }
        return res;
    }

    // TODO: 双 Stack 解法

    public static void main(String[] args) {
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{3,9,20,null,null,15,7});
        LeetCode103 leetCode103 = new LeetCode103();
        List<List<Integer>> res = leetCode103.zigzagLevelOrder(tree);
        System.out.println(res);
    }
}
