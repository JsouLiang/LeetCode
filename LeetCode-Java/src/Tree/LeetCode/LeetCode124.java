package Tree.LeetCode;

/**
 * 124. 二叉树中的最大路径和
 * 路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */
public class LeetCode124 {
    private int MAX_VALUE = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return MAX_VALUE;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        /// 如果 left 分支 <0 的话，则当前节点不需要左子树贡献值，即left为 0
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        int currentValue = root.val + right + left;
        MAX_VALUE = Math.max(currentValue, MAX_VALUE);
        return Math.max(root.val + left, root.val + right);
    }

    public static void main(String[] args) {
        LeetCode124 leetCode124 = new LeetCode124();
        TreeNode treeNode = TreeNode.createTreeWith(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1});
        leetCode124.maxPathSum(treeNode);
    }
}
