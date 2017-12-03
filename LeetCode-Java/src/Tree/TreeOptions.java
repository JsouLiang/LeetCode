package Tree;

/**
 * Tree
 * Created by X-Liang
 * 2017-12-03-17:22
 *
 * @Description: 树的基本操作
 */
public class TreeOptions {

    /**
     * 前序遍历
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getVal());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getVal());
        inOrder(root.getRight());
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getVal());
    }


    /**
     * 通过前序和中序遍历，输出后序遍历
     * @param preOrder  前序遍历
     * @param inOrder 中序遍历
     * @return  后序遍历
     */
    public String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return "";
        }

        char rootValue = preOrder.charAt(0);
        // 前序遍历的第一个节点，在中序遍历中可以确定根节点左边的为左子树，右边为右子树
        int rootIndexAtInOrder = inOrder.indexOf(rootValue);
        // 此时通过 rootIndexAtInOrder 会得到一个半开半闭区间，在中序遍历中[0, rootIndexAtInOrder) 为左子树，前序遍历中[1, rootIndexAtInOrder + 1) 为左子树

        return postOrder(
                preOrder.substring(1, rootIndexAtInOrder + 1),
                inOrder.substring(0, rootIndexAtInOrder)
        ) + postOrder(
                preOrder.substring(1 + rootIndexAtInOrder),
                inOrder.substring(1 + rootIndexAtInOrder)
        ) + rootValue;
    }

    /**
     * 寻找指点节点在中序遍历时下一个节点的值
     * @param node 指定的节点
     * @return 中序遍历时下一个节点
     */
    public TreeNode next(TreeNode node) {
        if (node == null) {
            return null;
        }
        // 如果指点的节点右子树不为空，则返回右子树中序遍历是第一个节点
        if (node.getRight() != null) {
            return getFirstNodeOfInOrder(node.getRight());  // 返回右子树的第一个节点
        } else {    // 往父节点走，直到某个父节点是作为左孩子存在，那么返回这个父节点的父节点
            while (node.getParent() != null &&
                    node.getParent().getRight() == node) {   
                node = node.getParent();
            }
            // node.parent == null || node is left child of its parent
            return node.getParent();
        }
    }

    /**
     * 获得指定的树中序遍历时第一个节点
     * @param root 指定一棵树
     * @return
     */
    private TreeNode getFirstNodeOfInOrder(TreeNode  root) {
        if (root == null) {
            return null;
        }

        TreeNode currentNode = root;
        while (currentNode != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    public static void main(String[] args) {

    }
}
