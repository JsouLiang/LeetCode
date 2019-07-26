package Tree;

import java.util.ArrayList;

/**
 * Tree
 * Created by X-Liang
 * 2017-10-02-22:25
 *
 * @Description:
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode getParent() {
        return parent;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
        if (this.left != null) {
            this.left.setParent(this);
        }

    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    public TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.getLeft().setLeft(new TreeNode((4)));
        root.getLeft().setRight(new TreeNode(5));
        root.getLeft().getRight().setLeft(new TreeNode(7));
        root.setRight(new TreeNode(3));
        root.getRight().setRight(new TreeNode(6));
        return root;
    }

    /**
     * 根据前序遍历和中序遍历创建一棵树
     * @param preOrder 前序遍历
     * @param inOrder  中序遍历
     * @return
     */
    public TreeNode createTree(String preOrder, String inOrder) {

        if (preOrder.isEmpty()) {
            return null;
        }

        char rootValue = preOrder.charAt(0);
        // 前序遍历的第一个节点，在中序遍历中可以确定根节点左边的为左子树，右边为右子树
        int rootIndexAtInOrder = inOrder.indexOf(rootValue);
        // 此时通过 rootIndexAtInOrder 会得到一个半开半闭区间，在中序遍历中[0, rootIndexAtInOrder) 为左子树，前序遍历中[1, rootIndexAtInOrder + 1) 为左子树

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(String.valueOf(rootValue)));
        root.setLeft(
                createTree(
                        preOrder.substring(1, rootIndexAtInOrder + 1),
                        inOrder.substring(0, rootIndexAtInOrder)));
        root.setRight(
                createTree(
                        preOrder.substring(1 + rootIndexAtInOrder),
                        inOrder.substring(1 + rootIndexAtInOrder)));
        return root;
    }

}
