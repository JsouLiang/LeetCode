package Tree.LeetCode;

import Link.leetcode.easy.ListNode;
import com.sun.security.auth.UnixNumericGroupPrincipal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 04.03. 特定深度节点链表
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 * 示例：
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 */
public class ListOfTreeDepth {
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[]{};
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        List<ListNode> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentLevelCount = queue.size();
            ListNode dummyNode = new ListNode(-1);
            ListNode current = dummyNode;
            for (int i = 0; i < currentLevelCount; i++) {
                TreeNode treeNode = queue.poll();
                ListNode node = new ListNode(treeNode.val);
                current.next = node;
                current = node;
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            res.add(dummyNode.next);
        }
        ListNode[] lists = new ListNode[res.size()];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = res.get(i);
        }
        return lists;
    }

    public static void main(String[] args) {
        ListOfTreeDepth listOfTreeDepth = new ListOfTreeDepth();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1, 2, 3, 4, 5, null, 7, 8});
        listOfTreeDepth.listOfDepth(tree);
    }
}
