package Tree.LeetCode;

import Link.leetcode.LeetCode25;

/**
 * 250. 统计同值子树
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 */
public class LeetCode250 {
    public int countUnivalSubtrees(TreeNode root) {
        Res res = helper(root);
        return res.count;
    }

    class Res {
        int count;
        boolean isUnivalSubtrees;
        public Res(int count, boolean isUnivalSubtrees) {
            this.count = count;
            this.isUnivalSubtrees = isUnivalSubtrees;
        }
    }

    private Res helper(TreeNode root) {
        /// null
        if (root == null) {
            return new Res(0, true);
        }
        /// 叶子节点
        if (root.left == null && root.right == null) {
            return new Res(1, true);
        }
        Res leftRes = helper(root.left);
        Res rightRes = helper(root.right);
        if (root.left != null && root.right != null) {
            if (leftRes.isUnivalSubtrees && rightRes.isUnivalSubtrees && root.left.val == root.val && root.val == root.right.val ) {
                return new Res(leftRes.count + rightRes.count + 1,  true);
            } else {
                return new Res(leftRes.count + rightRes.count, false);
            }
        } else if (root.left != null ){
            if (leftRes.isUnivalSubtrees && root.left.val == root.val) {
                return new Res(leftRes.count + 1,  true);
            } else {
                return new Res(leftRes.count, false);
            }
        } else {
            if (rightRes.isUnivalSubtrees && root.right.val == root.val) {
                return new Res(rightRes.count + 1,  true);
            } else {
                return new Res(rightRes.count,  false);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode250 leetCode250 = new LeetCode250();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{5, 1, 5, 5, 5, null, 5});
        leetCode250.countUnivalSubtrees(tree);
    }
}
