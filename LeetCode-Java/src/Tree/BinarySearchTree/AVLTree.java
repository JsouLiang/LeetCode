package Tree.BinarySearchTree;

public class AVLTree<E> extends BinarySearchTree {
    public AVLTree(Node parent, Object element) {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(Object element, Node parent) {
        AVLNode node = new AVLNode<>(parent, element);
        return node;
    }

    @Override
    /**
     * AVL树平衡操作
     * 1. 通过Parent 往上找，找到第一个失衡的节点，进行平衡操作
     */
    protected void afterAddAction(Node node) {
        ((AVLNode)node).height = 0;     // 叶子节点的height = 0;
        ((AVLNode)node.parent).height = 1;  // 更新父节点的 height

        while ((node = node.parent) != null) {
            // node 是否平衡
            if (isBalance(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 恢复平衡
     * @param node 第一个失衡的节点
     */
    private void rebalance(Node<E> node) {
        AVLNode avlNode = ((AVLNode<E>) node);

    }

    /**
     * 节点是否平衡：节点的平衡因子绝度值 <= 1
     * @param node
     * @return
     */
    private boolean isBalance(Node<E> node) {
       return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {
        int height;

        public AVLNode(Node<E> parent, E element) {
            super(parent, element);
        }

        public AVLNode(Node<E> parent, Node<E> left, Node<E> right, E element) {
            super(parent, left, right, element);
        }

        /**
         * 平衡因子: 左子树高度 - 右子树高度
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight < rightHeight) {
                return right;
            } else {
                return isLeftChild() ? left : right;
            }
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }
    }
}
