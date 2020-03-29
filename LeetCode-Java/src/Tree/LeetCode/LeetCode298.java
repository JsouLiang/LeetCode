package Tree.LeetCode;

import Link.MaxSlidingWindow;
import jdk.nashorn.api.tree.Tree;

import java.util.List;

/**
 * 298. 二叉树最长连续序列
 */
public class LeetCode298 {
    Integer maxValue;

    public void setMaxValue(int maxValue) {
        if (this.maxValue == null) {
            this.maxValue = maxValue;
        }
        this.maxValue = Math.max(maxValue, this.maxValue);
    }

    public int longestConsecutive(TreeNode root) {
        this.setMaxValue(Integer.MIN_VALUE);
        helper(root);
        return maxValue;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            this.setMaxValue(0);
            return 0;
        }
        if (root.left == null && root.right == null) {
            this.setMaxValue(1);
            return 1;
        }
        /// 以 root 为起点的最长连续序列
        int leftResult = helper(root.left);
        int rightResult = helper(root.right);
        boolean isIncreaseLeft = isIncrease(root, root.left);
        boolean isIncreaseRight = isIncrease(root, root.right);
        int res;
        if (isIncreaseLeft && isIncreaseRight) {
            res = Math.max(leftResult + 1, rightResult + 1);
        } else if (isIncreaseLeft) {
            res = leftResult + 1;
        } else if (isIncreaseRight) {
            res = rightResult + 1;
        } else {
            res = 1;
        }
        this.setMaxValue(res);
        return res;
    }

    private boolean isIncrease(TreeNode root, TreeNode child) {
        if (child == null) {
            return false;
        }
        if (root.val + 1 != child.val) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode298 leetCode298 = new LeetCode298();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1, 3, null, null, 4});
        leetCode298.longestConsecutive(tree);
    }
}
