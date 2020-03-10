package Tree.LeetCode;

import Basic.string.StringAlgo;
import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 */
public class LeetCode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return _buildTree(preorder, 0, inorder);
    }

    private TreeNode _buildTree(int[] preorder, int preorderRootIndex, int[] inorder) {
        if (inorder.length == 0) {
            return null;
        }
        if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }
        final TreeNode rootNode = new TreeNode(preorder[preorderRootIndex]);

        /// 从中序遍历中到当前根节点，当前根节点左边的元素是左子树，右边的元素是右子树
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            /// 找到根节点
            if (inorder[i] == preorder[preorderRootIndex]) {
                rootIndex = i;
                break;
            }
        }
        int[] leftNodes = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightNodes = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        rootNode.left = _buildTree(preorder, preorderRootIndex + 1, leftNodes);
        rootNode.right = _buildTree(preorder, preorderRootIndex + leftNodes.length + 1, rightNodes);
        return rootNode;
    }

    public static void main(String[] args) {
        LeetCode105 leetCode105 = new LeetCode105();
        TreeNode node = leetCode105.buildTree(new int[]{3,9,8}, new int[]{8,9,3});
        System.out.println(node);
    }
}
