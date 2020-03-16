package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

public class LeetCode156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode upsideRoot = dfs(root);
        return upsideRoot;
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode upsideRoot = dfs(root.left);
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode right = root.right;
        root.left.left = right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return upsideRoot;
    }

    public static void main(String[] args) {
        LeetCode156 leetCode156 = new LeetCode156();
        TreeNode root = TreeNode.createTreeWith(new Integer[]{1, 2, 3, 4, 5, null, null});
        leetCode156.upsideDownBinaryTree(root);
    }
}
