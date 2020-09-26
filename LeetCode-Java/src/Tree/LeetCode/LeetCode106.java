package Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 */
public class LeetCode106 {
    /**
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return _buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode _buildTree(List<Integer> inorder, List<Integer> postorder) {
        int currentNodeVal = postorder.get(postorder.size() - 1);
        TreeNode node = new TreeNode(currentNodeVal);
        List<Integer> inorderLeft = new ArrayList<>();
        int left;
        for (left = 0; left < inorder.size(); left++) {
            if (inorder.get(left) == currentNodeVal) {
                break;
            }
            inorderLeft.add(inorder.get(left));
        }
        List<Integer> inorderRight = new ArrayList<>();
        int right;
        for (right = left + 1; right < inorder.size(); right++) {
            inorderRight.add(inorder.get(right));
        }
        List<Integer> postorderLeft = new ArrayList<>();
        for (int i = 0; i < left; i++) {
            postorderLeft.add(postorder.get(i));
        }
        List<Integer> postorderRight = new ArrayList<>();
        for (int j = left + 1; j < postorder.size() - 1; j++) {
            postorderRight.add(postorder.get(j));
        }
        node.left = _buildTree(inorderLeft, postorderLeft);
        node.right = _buildTree(inorderRight, postorderRight);
        return node;
    }

    private TreeNode _buildTree(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd) {
        if (inorderEnd - inorderStart != postorderEnd - postorderStart || inorderStart > inorderEnd) {
            return null;
        }
        if (inorderStart == inorderEnd) {
            return new TreeNode(inorder[inorderStart]);
        }
        TreeNode node = new TreeNode(postorder[postorderEnd]);
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == postorder[postorderEnd]) {
                int leftChildrenCount = i - inorderStart;
                node.left = _buildTree(inorder, inorderStart, i - 1, postorder, postorderStart, postorderStart + leftChildrenCount - 1);
                node.right = _buildTree(inorder, i + 1, inorderEnd, postorder, postorderStart + leftChildrenCount, postorderEnd - 1);
                break;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        LeetCode106 leetCode106 = new LeetCode106();
        leetCode106.buildTree(new int[]{}, new int[]{});
    }
}
