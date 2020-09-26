package Tree.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 */
public class LeetCode199 {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = bfs(root);
        return res;
    }

    private List<Integer> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            /// 当前层节点个数
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                /// 从队首取出当前节点
                TreeNode node = queue.poll();
                if (i == currentLevelSize - 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode199 leetCode199 = new LeetCode199();
        TreeNode treeNode = TreeNode.createTreeWith(new Integer[]{});
        leetCode199.rightSideView(treeNode);
    }
}
