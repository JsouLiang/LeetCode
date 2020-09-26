package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

/**
 * 450. 删除二叉搜索树中的节点
 */
public class LeetCode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            /// 找出左子树中最大值
            TreeNode leftMaxNode = root.left;
            while (leftMaxNode.right != null) {
                leftMaxNode = leftMaxNode.right;
            }
            leftMaxNode.right = root.right;
            return root.left;
        }
        return root;
    }

    public static void main(String[] args) {
        LeetCode450 leetCode450 = new LeetCode450();
        TreeNode treeNode = TreeNode.createTreeWith(new Integer[]{6, 4, 8, 3, 5, 7, 9});
        treeNode = leetCode450.deleteNode(treeNode, 6);
        System.out.println(treeNode);
    }

}
