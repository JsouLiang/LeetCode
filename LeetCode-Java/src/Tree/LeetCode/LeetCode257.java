package Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class LeetCode257 {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.add(root.val+"");
            return result;
        }
        List<String> leftChildrenPath = binaryTreePaths(root.left);
        List<String> rightChildrenPath = binaryTreePaths(root.right);

        for (int i = 0; i < leftChildrenPath.size(); i++) {
            leftChildrenPath.set(i, root.val+"->" + leftChildrenPath.get(i));
        }

        for (int i = 0; i < rightChildrenPath.size(); i++) {
            rightChildrenPath.set(i, root.val+"->" + rightChildrenPath.get(i));
        }
        result.addAll(leftChildrenPath);
        result.addAll(rightChildrenPath);


        return result;
    }

    public static void main(String[] args) {
        LeetCode257 leetCode257 = new LeetCode257();
        TreeNode root = TreeNode.createTreeWith(new Integer[]{1,2,3,null,5});
        List<String> result = leetCode257.binaryTreePaths(root);
        System.out.println(result);
    }
}
