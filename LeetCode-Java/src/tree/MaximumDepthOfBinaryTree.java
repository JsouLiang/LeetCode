package Tree;

/**
 * Tree
 * Created by X-Liang
 * 2017-10-02-22:59
 *
 * @Description:
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
