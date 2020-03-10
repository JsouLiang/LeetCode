package Tree.LeetCode;

import Link.leetcode.medium.LeetCode2;
import jdk.nashorn.api.tree.Tree;

public class LeetCode270 {
    public int closestValue(TreeNode root, double target) {
        int val = findTarget(root, target);
        return val;
    }

    private int findTarget(TreeNode root, double target) {
        /// 当前节点左子树中最接近 target 的元素
        int leftVal = Integer.MAX_VALUE;
        if (root.left != null) {
            leftVal = findTarget(root.left, target);
        }
        /// 当前节点右子树中最接近 target 的元素
        int rightVal = Integer.MAX_VALUE;
        if (root.right != null) {
            rightVal = findTarget(root.right, target);
        }
        /// 三者取最接近的
        double rootDelta = Math.abs(root.val - target);
        double leftDelta = Math.abs(leftVal - target);
        double rightDelta = Math.abs(rightVal - target);

        if (root.left != null && leftDelta < rootDelta && leftDelta < rightDelta) {
            return leftVal;
        } else if (root.right != null && rightDelta < rootDelta && rightDelta < leftDelta) {
            return rightVal;
        } else {
            return root.val;
        }

//        if (root.val > target) {
//            int leftVal = root.left != null ? root.left.val : Integer.MAX_VALUE;
//            double rootDelta = root.val - target;
//            if (Math.abs(leftVal - target) < rootDelta && root.left != null) {
//                return findTarget(root.left, target);
//            }
//            return root.val;
//        } else {
//            int rightVal = root.right != null ? root.right.val : Integer.MAX_VALUE;
//            double rootDelta = target - root.val;
//            if (Math.abs(rightVal - target) < rootDelta && root.right != null) {
//                return findTarget(root.right, target);
//            }
//            return root.val;
//        }
    }

    public static void main(String[] args) {
        LeetCode270 leetCode270 = new LeetCode270();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{4, 2, 5, 1, 3});
//        int res = leetCode270.closestValue(tree, 2.9);
//        res = leetCode270.closestValue(tree, 3.714);
//        tree = TreeNode.createTreeWith(new Integer[]{0});
//        leetCode270.closestValue(tree, 2147483648.0);
        tree = TreeNode.createTreeWith(new Integer[]{36,13,37,4,20,null,48,1,5,17,33,43,49,0,2,null,9,14,18,22,34,40,46,null,null,null,null,null,3,7,11,null,16,null,19,21,27,null,35,39,42,45,47,null,null,6,8,10,12,15,null,null,null,null,null,26,32,null,null,38,null,41,null,44,null,null,null,null,null,null,null,null,null,null,null,null,null,24,null,28,null,null,null,null,null,null,null,23,25,null,29,null,null,null,null,null,31,30,});
        leetCode270.closestValue(tree, 3.142857);
    }
}
