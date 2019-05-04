package Tree.BinarySearchTree;

import 二分查找.BinarySearch;

public class BinarySearchTree<E> {
    private int size;
    private Node<E> root;

    Comparator<E> comparator;

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

    public void add(E element) {
        if (root == null) {
            root = new Node<>(null, element);
            size++;
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

            }
        }

        Node insertedNode = new Node(parentNode, element);
        if (comparedValue > 0) {
            parentNode.left = insertedNode;
        } else if (comparedValue < 0) {
            parentNode.right = insertedNode;
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

    private static class Node<E> {
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
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(1);
        binarySearchTree.add(2);
        binarySearchTree.add(3);
    }
}
