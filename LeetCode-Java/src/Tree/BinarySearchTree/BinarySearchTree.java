package Tree.BinarySearchTree;

import 二分查找.BinarySearch;

import java.util.*;

public class BinarySearchTree<E> extends BinaryTree {

    public static interface Visitor<E> {
        void visite(E element);
    }

    private int size;
    private Node<E> root;

    Comparator<E> comparator;

    public int height() {
        return height(root);
    }

    /**
     * 树的高度
     * @param root
     * @return
     */
    private int height(Node<E> root) {
//        return Math.max(height(node.left), height(node.right)) + 1;
        /**
         * 输的高度非递归实现
         */
        // 🌲的高度
        int height = 0;
        // 每一层节点的个数
        int levelSize = 1;      // 第一层有1个元素
        Queue<Node<E>> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 访问下一层
            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /**
     * 是否是完全二叉树
     * 使用层序遍历的方式
     * @return
     */
    boolean isFullBinaryTree(Node<E> root) {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && node.isLeaf()) {
                  return false;
            }
            // node.left != null && node.right != null
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                // 后续节点均为叶子节点
                // node.left != null && node.right== null
                // node.left == null && node.right == null
                leaf = true;
            }
        }
        return true;
    }

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public boolean isEmypty() {
        return size == 0;
    }

    public void clear() {

    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(parent, element);
    }

    public void add(E element) {
        if (root == null) {
            root = createNode(element, null);
            size++;
            // 新添加了一个根节点
            afterAddAction(root);
            return;
        }
        Node<E> currentNode = root;
        Node<E> parentNode = root;
        int comparedValue = 0;

        while (currentNode != null) {
            parentNode = currentNode;
            comparedValue = compare(currentNode.element, element);
            if (comparedValue < 0) {   // currentElement < element
                currentNode = currentNode.right;
            } else if (comparedValue > 0) { // currentElement > element
                currentNode = currentNode.left;
            } else {
                currentNode.element = element;  // 相等的情况下，进行覆盖
                return ;
            }
        }

        Node insertedNode = createNode(element, parentNode);
        if (comparedValue > 0) {
            parentNode.left = insertedNode;
        } else if (comparedValue < 0) {
            parentNode.right = insertedNode;
        }
        // 新添加节点之后的处理
        afterAddAction(insertedNode);
    }

    /**
     * 添加node 节点之后对树的操作
     * @param node 新添加的节点
     */
    protected void afterAddAction(Node<E> node) {}

    /**
     * 前序遍历
     * 根-左-右
     *    |
     *    根-左-右
     *       |  |
     *      ... 根-左-右
     */
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return ;
        }
        // action
        visitor.visite(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 前序遍历非递归实现
     * @param root
     * @return
     */
    public List<E> preorderTraversal(Node<E> root) {
       if (root == null) {
           return null;
       }
       List<E> res = new ArrayList<>();
       Stack<Node<E>> stack = new Stack<>();

       stack.push(root);
       while (!stack.isEmpty()) {
           Node<E> node = stack.pop();
           res.add(node.element);

           if (node.right != null) {
               stack.push(node.right);
           }
           if (node.left != null) {
               stack.push(node.left);
           }
       }

       return res;
    }

    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    public List<E> inorderTraversal(Node<E> node) {
        List<E> res = new ArrayList<>();

        if (node == null) {
            return res;
        }
        Stack<Node<E>> stack = new Stack<>();
        Node<E> current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node<E> top = stack.pop();
                res.add(top.element);
                current = top.right;
            }
        }
        return res;
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        inorderTraversal(node.left, visitor);
        // action
        visitor.visite(node.element);
        inorderTraversal(node.right, visitor);
    }



    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        //action
        visitor.visite(node.element);
    }

    public void levelorderTraversal(Node<E> root, Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // action node action
            visitor.visite(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public boolean contains(E element) {
        return false;
    }



    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element not null");
        }
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(Node<E> parent, E element) {
            this(parent, null, null, element);
        }

        public Node(Node<E> parent, Node<E> left, Node<E> right, E element) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.element = element;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(1);
        binarySearchTree.add(2);
        binarySearchTree.add(3);
    }
}
