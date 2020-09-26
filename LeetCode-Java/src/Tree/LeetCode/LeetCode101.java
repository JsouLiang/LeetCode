package Tree.LeetCode;

/**
 * 101. 对称二叉树
 */
public class LeetCode101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }

    private boolean check(TreeNode node) {
        if (node == null) {
            return false;
        }
        if (node.left != null && node.right != null) {
            boolean res1 = node.left.val == node.right.val;
            boolean res2 = true;
            if (node.left.left != null) {
                if (node.right.right == null) {
                    return false;
                }
                res2 = node.left.left.val == node.right.right.val;
            }

            boolean res3 = true;
            if (node.left.right != null) {
                if (node.right.left == null) {
                    return false;
                }
                res3 = node.left.right.val == node.right.left.val;
            }
            return res1 && res2 && res3;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode101 leetCode101 = new LeetCode101();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1,2,2,3,4,4,3});
        leetCode101.isSymmetric(tree);
    }
}
