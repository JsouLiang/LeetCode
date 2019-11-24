package 暴力搜索;

import Tree.LeetCode.TreeNode;

import java.util.Arrays;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5],
 * which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class LeetCode108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode tree = solute(nums);
        return tree;
    }

    private TreeNode solute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int middle = nums.length / 2;
        TreeNode rootNode = new TreeNode(nums[middle]);
        // left array
        // [left, right)
        final int[] leftArray = Arrays.copyOfRange(nums, 0, middle);
        rootNode.left = solute(leftArray);

        final int left = middle + 1 >= nums.length ? nums.length : middle + 1;
        final int[] rightArray = Arrays.copyOfRange(nums, left, nums.length);
        // right array
        rootNode.right = solute(rightArray);
        return rootNode;
    }

    public static void main(String[] args) {
        LeetCode108 leetCode108 = new LeetCode108();
        leetCode108.sortedArrayToBST(new int[]{-10,-3,0,5,9});
    }
}
