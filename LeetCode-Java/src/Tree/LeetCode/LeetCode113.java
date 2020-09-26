package Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 */
public class LeetCode113 {
    private List<List<Integer>> res;
    private int sum;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        this.sum = sum;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, root.val + root.left.val, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, root.val + root.right.val, path);
            path.remove(path.size() - 1);
        }
        return res;
    }

    private void dfs(TreeNode node, int currentSum, List<Integer> path) {
        if (node == null) {
            return;
        }
        if (currentSum == sum && node.left == null && node.right == null) {
            res.add(List.copyOf(path));
            return;
        }
        if (node.left != null) {
            path.add(node.left.val);
            dfs(node.left, currentSum + node.left.val, path);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            path.add(node.right.val);
            dfs(node.right, currentSum + node.right.val, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode113 leetCode113 = new LeetCode113();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1});
        leetCode113.pathSum(tree, 1);
    }
}
