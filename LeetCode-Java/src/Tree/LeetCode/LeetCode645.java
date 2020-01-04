package Tree.LeetCode;

import Stack.单调栈.LeetCode84;
import jdk.nashorn.api.tree.Tree;

import java.util.Stack;

/**
 * 654. 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 */
public class LeetCode645 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
//        return constructTree(nums, 0, nums.length - 1);
        return generatorTree(nums);
    }

    private TreeNode constructTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        /// 1. find largest num
        int maxNumIndex = 0, max = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxNumIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        TreeNode leftTree = constructTree(nums, left, maxNumIndex - 1);
        TreeNode rightTree = constructTree(nums, maxNumIndex + 1, right);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    private TreeNode generatorTree(int[] nums) {
        Stack<TreeNode> minimumStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode current = new TreeNode(nums[i]);
            while (!minimumStack.isEmpty() && minimumStack.peek().val < nums[i]) {
                current.left = minimumStack.peek();
                minimumStack.pop();
            }
            if (!minimumStack.isEmpty()) {
                minimumStack.peek().right = current;
            }

            minimumStack.push(current);
        }
        return minimumStack.firstElement();
    }

    public static void main(String[] args) {
        LeetCode645 leetCode645 = new LeetCode645();
        leetCode645.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
    }
}
