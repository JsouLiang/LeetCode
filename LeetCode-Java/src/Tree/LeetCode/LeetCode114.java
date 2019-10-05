package Tree.LeetCode;

import Basic.BinarySearch.LeetCode35;
import jdk.nashorn.api.tree.Tree;

/**
 * 114. Flatten Binary Tree to Linked List
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LeetCode114 {

    class ResultType {
        TreeNode head;
        TreeNode tail;
        public ResultType() {}
        public ResultType(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    public void flatten(TreeNode root) {
        ResultType resultType = divideConquer(root);
        TreeNode node = resultType.head;
        while (node != null) {
            System.out.print(node.val);
            System.out.print(' ');
            node = node.right;
        }
        System.out.println(resultType);

    }

    private ResultType divideConquer(TreeNode node) {
        if (node == null) {
            return null;
        }
        // 将 node.left flatten 一条链表
        ResultType leftLink = divideConquer(node.left);
        // 将 node.right flatten 一条链表
        ResultType rightLink = divideConquer(node.right);
        // 链表合并
        TreeNode tail = node;
        if (leftLink != null) {
            node.right = leftLink.head;
            tail = leftLink.tail;
            node.left = null;
        }
        if (rightLink != null) {
            if (leftLink != null) {
                leftLink.tail.right = rightLink.head;
            }
            tail = rightLink.tail;
        }

        ResultType resultType = new ResultType(node, tail);
        return resultType;
    }

    public static void main(String[] args) {
        LeetCode114 leetCode114 = new LeetCode114();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1, 2, 5, 3, 4, null, 6});
        leetCode114.flatten(tree);
        /**
         *      1
         *     /
         *    2
         *   / \
         *  3   4
         *  /
         * 5
         */
        tree = TreeNode.createTreeWith(new Integer[]{1,2,null,3,4,5});
        leetCode114.flatten(tree);
        /**
         *       3
         *      / \
         *     1   4
         *      \
         *       2
         */
        tree = TreeNode.createTreeWith(new Integer[]{3,1,4,null,2});
        leetCode114.flatten(tree);
    }
}
