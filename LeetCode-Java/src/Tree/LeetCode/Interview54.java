package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

/**
 * 剑指 offer 54：二叉搜索树的第k大节点
 */
public class Interview54 {
    Integer targetVal;
    int count;
    public int kthLargest(TreeNode root, int k) {
        count = 0;
        findKthNums(root, k);
        return targetVal;
    }

    private void findKthNums(TreeNode root, int k) {
        if (root == null || targetVal != null) {
            return;
        }
        findKthNums(root.right, k);
        count++;
        if (count == k) {
            targetVal = root.val;
            return ;
        }
        findKthNums(root.left, k);
    }
    public static void main(String[] args) {
        Interview54 interview54 = new Interview54();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{3, 1, 4, null, 2});
        interview54.kthLargest(tree, 1);
        tree = TreeNode.createTreeWith(new Integer[]{5,3,6,2,4,null,null,1});
        interview54.kthLargest(tree, 1);
    }
}
