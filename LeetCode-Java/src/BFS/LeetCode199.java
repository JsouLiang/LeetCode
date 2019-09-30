package BFS;

import Tree.LeetCode.TreeNode;
import jdk.nashorn.api.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class LeetCode199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final int curretLevelSize = queue.size();
            for (int i = 0; i < curretLevelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (i == curretLevelSize - 1) {
                    res.add(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1,2,3,null,5,null,4});

        LeetCode199 leetCode199 = new LeetCode199();
        List<Integer> res =  leetCode199.rightSideView(tree);
        System.out.println(res);
    }
}
