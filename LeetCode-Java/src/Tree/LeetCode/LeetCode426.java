package Tree.LeetCode;

/**
 * 426. 将二叉搜索树转化为排序的双向链表
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
public class LeetCode426 {

    private class ResultType {
        Node head;
        Node tail;
        public ResultType(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public Node treeToDoublyList(Node root) {
        return converTreeToLink(root).head;
    }

    private ResultType converTreeToLink(Node root) {
        if (root == null) {
            return new ResultType(null, null);
        }
        ResultType leftSubtreeLinkedList = converTreeToLink(root.left);
        ResultType rightSubtreeLinkedList = converTreeToLink(root.right);
        /// Node 的 left 指向前驱，right 指向后继
        if (leftSubtreeLinkedList.tail != null) {
            leftSubtreeLinkedList.tail.right = root;
            root.left = leftSubtreeLinkedList.tail;
        }

        if (rightSubtreeLinkedList.head != null) {
            root.right = rightSubtreeLinkedList.head;
            rightSubtreeLinkedList.head.left = root;
        }
        Node head, tail;
        if (root.left != null && root.right != null) {
            head = leftSubtreeLinkedList.head; tail = rightSubtreeLinkedList.tail;
        } else {
            if (root.left != null) {
                head = leftSubtreeLinkedList.head; tail = root;
            } else if (root.right != null) {
                head = root; tail = rightSubtreeLinkedList.tail;
            } else {
                head = tail = root;
            }
        }

        head.left = tail;
        tail.right = head;
        return new ResultType(head, tail);
    }

    public static void main(String[] args) {
        Node four = new Node(4, null, null);
        Node two = new Node(2, null, null);
        Node five = new Node(5, null, null);
        Node one = new Node(1, null, null);
        Node three = new Node(3, null, null);
        two.left = one; two.right = three;
        four.left = two; four.right = five;

        LeetCode426 leetCode426 = new LeetCode426();
        Node head = leetCode426.treeToDoublyList(four);

        two = new Node(2, null, null);
        one = new Node(1, null, null);
        two.left = one;
        head = leetCode426.treeToDoublyList(two);

        System.out.println(head);
    }
}
